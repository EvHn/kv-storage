package khannanov.kvstorage.repository;

import khannanov.kvstorage.model.EntryChange;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EntryChangeRepository extends AbstractRepository<EntryChange> {

    public EntryChange getById(int id) {
        return sessionFactory.getCurrentSession().get(EntryChange.class, id);
    }

    public List<EntryChange> getByKey(String key) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EntryChange> cq = cb.createQuery(EntryChange.class);
        Root<EntryChange> root = cq.from(EntryChange.class);
        cq.select(root).where(cb.like(root.get(EntryChange.KEY), key)).orderBy(cb.asc(root.get(EntryChange.CREATED)));

        return session.createQuery(cq).getResultList();
    }
}
