package khannanov.kvstorage.impl;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryChange;

public class SimpleDiffer implements IDiffer {

    @Override
    public EntryChange calc(Entry oldEntry, Entry newEntry) {
        EntryChange entryChange = new EntryChange();
        if(oldEntry.getKey().equals(newEntry.getKey())) {
            entryChange.setKey(oldEntry.getKey());
            entryChange.setCreated(oldEntry.getChanged());
            entryChange.setValue(oldEntry.getValue());
        }
        return entryChange;
    }

    @Override
    public Entry apply(Entry entry, EntryChange entryChange) {
        Entry lastEntry = new Entry();
        if(entry.getKey().equals(entryChange.getKey())) {
            entry.setKey(entryChange.getKey());
            entry.setValue(entryChange.getValue());
            entry.setChanged(entryChange.getCreated());
        }
        return lastEntry;
    }

}
