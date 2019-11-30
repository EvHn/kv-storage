package khannanov.kvstorage.service.impl;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryChange;

public interface IDiffer {
    EntryChange calc(Entry oldEntry, Entry newEntry);
    Entry apply(Entry entry, EntryChange entryChange);
}
