package khannanov.kvstorage.service;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.service.model.EntryHistory;
import org.springframework.security.core.userdetails.UserDetails;

public interface IStorageService {
    Entry getEntry(UserDetails user, String key);
    Iterable<Entry> getEntries(UserDetails user);
    EntryHistory getHistory(Entry entry);
    void add(Entry entry);
    void delete(Entry entry);
}
