package khannanov.kvstorage.repository;

import khannanov.kvstorage.model.Entry;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EntryRepository extends AbstractRepository<Entry> {

    public Entry getByKey(String key) {
        return sessionFactory.getCurrentSession().get(Entry.class, key);
    }

    public List<Entry> getEntries() {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Entry> cq = cb.createQuery(Entry.class);
        Root<Entry> root = cq.from(Entry.class);
        cq.select(root).orderBy(cb.asc(root.get(Entry.KEY)));

        return session.createQuery(cq).getResultList();
    }
}
