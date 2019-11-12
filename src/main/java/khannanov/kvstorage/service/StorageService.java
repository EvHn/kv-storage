package khannanov.kvstorage.service;

import khannanov.kvstorage.impl.IDiffer;
import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryChange;
import khannanov.kvstorage.model.EntryHistory;
import khannanov.kvstorage.repository.EntryChangeRepository;
import khannanov.kvstorage.repository.EntryRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Getter
public class StorageService implements IStorageService {

    private EntryRepository entryRepository;
    private EntryChangeRepository entryChangeRepository;
    private IDiffer differ;

    @Autowired
    public void setDiffer(IDiffer differ) {
        this.differ = differ;
    }

    @Autowired
    public void setEntryRepository(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Autowired
    public void setEntryChangeRepository(EntryChangeRepository entryChangeRepository) {
        this.entryChangeRepository = entryChangeRepository;
    }

    @Override
    public Entry getEntry(String key) {
        return entryRepository.getByKey(key);
    }

    @Override
    public List<Entry> getEntries() {
        return entryRepository.getEntries();
    }

    @Override
    public EntryHistory getHistory(String key) {
        Entry entry = entryRepository.getByKey(key);
        List<EntryChange> entryChanges = entryChangeRepository.getByKey(key);
        List<String> history =
                entryChanges.stream().map(ec -> differ.apply(entry, ec).getValue()).collect(Collectors.toList());
        return new EntryHistory(key, history);
    }

    @Override
    public void add(Entry entry) {
        entryRepository.saveOrUpdate(entry);
    }

    @Override
    public void delete(String key) {
        entryRepository.delete(entryRepository.getByKey(key));
    }

}
