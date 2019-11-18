package khannanov.kvstorage.impl;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryChange;
import org.springframework.stereotype.Component;

@Component
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
            lastEntry.setKey(entryChange.getKey());
            lastEntry.setValue(entryChange.getValue());
            lastEntry.setChanged(entryChange.getCreated());
        }
        return lastEntry;
    }

}
