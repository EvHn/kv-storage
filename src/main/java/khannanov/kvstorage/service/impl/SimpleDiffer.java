package khannanov.kvstorage.service.impl;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryChange;
import org.springframework.stereotype.Component;

@Component
public class SimpleDiffer implements IDiffer {

    @Override
    public EntryChange calc(Entry oldEntry, Entry newEntry) {
        if(oldEntry.getKey().equals(newEntry.getKey())) {
            return EntryChange.builder()
                    .entry(oldEntry)
                    .created(oldEntry.getChanged())
                    .value(oldEntry.getValue()).build();
        }
        return new EntryChange();
    }

    @Override
    public Entry apply(Entry entry, EntryChange entryChange) {
        if(entry.getKey().equals(entryChange.getEntry().getKey())) {
            return Entry.builder()
                    .key(entryChange.getEntry().getKey())
                    .value(entryChange.getValue())
                    .changed(entryChange.getCreated()).build();
        }
        return new Entry();
    }

}
