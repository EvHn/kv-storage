package khannanov.kvstorage.repository;

import khannanov.kvstorage.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IEntryRepository extends JpaRepository<Entry, String> {

    Iterable<Entry> getByUser(UserDetails user);
    Entry getByUserAndKey(UserDetails user, String key);
}
