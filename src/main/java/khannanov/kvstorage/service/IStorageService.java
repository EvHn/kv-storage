package khannanov.kvstorage.service;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryHistory;

import java.util.List;

public interface IStorageService {
    Entry getEntry(String key);
    List<Entry> getEntries();
    EntryHistory getHistory(String key);
    void add(Entry entry);
    void delete(String key);
}
