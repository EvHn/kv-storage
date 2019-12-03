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

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Iterator<EntryChange> entryChanges = entryChangeRepository.getByEntry(entry).iterator();
        List<Pair> history = Stream.iterate(differApply(entry, entryChanges), Objects::nonNull,
                e -> differApply(e)).map(e -> new Pair(e.getKey().getValue(), e.getKey().getChanged()))
                .collect(Collectors.toList());
        return new EntryHistory(entry.getKey(), history);
    }

    @Transactional
    @Override
    public void add(Entry entry) {
        entry.setChanged(new Timestamp(System.currentTimeMillis()));
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

    private Map.Entry<Entry, Iterator<EntryChange>> differApply(Map.Entry<Entry, Iterator<EntryChange>> e) {
        return e != null ? differApply(e.getKey(), e.getValue()) : null;
    }
    private Map.Entry<Entry, Iterator<EntryChange>> differApply(Entry entry, Iterator<EntryChange> iterator) {
        return iterator.hasNext() ? new HashMap.SimpleEntry<>(differ.apply(entry, iterator.next()), iterator) : null;
    }
}
