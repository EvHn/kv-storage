package khannanov.kvstorage.repository;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IEntryChangeRepository extends JpaRepository<EntryChange, Long> {
    Iterable<EntryChange> getByEntry(Entry entry);
}
