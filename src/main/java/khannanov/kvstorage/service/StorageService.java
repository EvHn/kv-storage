package khannanov.kvstorage.service;

import khannanov.kvstorage.impl.IDiffer;
import khannanov.kvstorage.impl.SimpleDiffer;
import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryChange;
import khannanov.kvstorage.model.EntryHistory;
import khannanov.kvstorage.model.Pair;
import khannanov.kvstorage.repository.EntryChangeRepository;
import khannanov.kvstorage.repository.EntryRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
@Getter
public class StorageService implements IStorageService {

    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private EntryChangeRepository entryChangeRepository;
    @Autowired
    private IDiffer differ;

    @Transactional
    @Override
    public Entry getEntry(String key) {
        return entryRepository.getByKey(key);
    }

    @Transactional
    @Override
    public List<Entry> getEntries() {
        return entryRepository.getEntries();
    }

    @Transactional
    @Override
    public EntryHistory getHistory(String key) {
        Entry entry = entryRepository.getByKey(key);
        List<EntryChange> entryChanges = entryChangeRepository.getByKey(key);
        List<Pair> history = new LinkedList<>();
        for(EntryChange ec : entryChanges) {
            entry = differ.apply(entry, ec);
            history.add(new Pair(entry.getValue(), entry.getChanged()));
        }
        return new EntryHistory(key, history);
    }

    @Transactional
    @Override
    public void add(Entry entry) {
        Entry oldEntry = entryRepository.getByKey(entry.getKey());
        if(oldEntry != null) {
            entryChangeRepository.save(differ.calc(oldEntry, entry));
            entryRepository.update(entry);
            return;
        }
        entryRepository.save(entry);
    }

    @Transactional
    @Override
    public void delete(String key) {
        entryRepository.delete(entryRepository.getByKey(key));
    }

}
