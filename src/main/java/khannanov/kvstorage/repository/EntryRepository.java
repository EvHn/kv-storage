package khannanov.kvstorage.repository;

import khannanov.kvstorage.model.Entry;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.util.List;

@Repository
public class EntryRepository extends AbstractRepository<Entry> {

    public Entry getByKey(String key) {
        return sessionFactory.getCurrentSession().get(Entry.class, key);
    }

    public List<Entry> getEntries() {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Entry> cq = cb.createQuery(Entry.class);
        Root<Entry> root = cq.from(Entry.class);
        cq.select(root).orderBy(cb.desc(root.get(Entry.KEY)));

        return session.createQuery(cq).getResultList();
    }

    @Override
    public void save(Entry entry) {
        entry.setChanged(new Date(System.currentTimeMillis()));
        super.save(entry);
    }

    @Override
    public void update(Entry entry) {
        entry.setChanged(new Date(System.currentTimeMillis()));
        super.update(entry);
    }
}
