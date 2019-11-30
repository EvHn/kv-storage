package khannanov.kvstorage.service;

import khannanov.kvstorage.service.impl.IDiffer;
import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryChange;
import khannanov.kvstorage.service.model.EntryHistory;
import khannanov.kvstorage.web.model.Pair;
import khannanov.kvstorage.repository.IEntryChangeRepository;
import khannanov.kvstorage.repository.IEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class StorageService implements IStorageService {

    @Autowired
    private IEntryRepository entryRepository;
    @Autowired
    private IEntryChangeRepository entryChangeRepository;
    @Autowired
    private IDiffer differ;

    @Transactional
    @Override
    public Entry getEntry(UserDetails user, String key) {
        return entryRepository.getByUserAndKey(user, key);
    }

    @Transactional
    @Override
    public Iterable<Entry> getEntries(UserDetails user) {
        return entryRepository.getByUser(user);
    }

    @Transactional
    @Override
    public EntryHistory getHistory(Entry entry) {
        Iterable<EntryChange> entryChanges = entryChangeRepository.getByEntry(entry);
        List<Pair> history = new LinkedList<>();
        for(EntryChange ec : entryChanges) {
            entry = differ.apply(entry, ec);
            history.add(new Pair(entry.getValue(), entry.getChanged()));
        }
        return new EntryHistory(entry.getKey(), history);
    }

    @Transactional
    @Override
    public void add(Entry entry) {
        if(entryRepository.existsById(entry.getKey())) {
            Entry oldEntry = entryRepository.getByUserAndKey(entry.getUser(), entry.getKey());
            entryChangeRepository.save(differ.calc(oldEntry, entry));
            entryRepository.save(entry);
            return;
        }
        entryRepository.save(entry);
    }

    @Transactional
    @Override
    public void delete(Entry entry) {
        entryRepository.delete(entry);
    }

}
