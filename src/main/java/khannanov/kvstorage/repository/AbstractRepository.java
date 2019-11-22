package khannanov.kvstorage.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractRepository<Data> implements IRepository<Data> {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void save(Data data) {
        sessionFactory.getCurrentSession().save(data);
    }

    @Override
    public boolean exist(Data data) {
        return sessionFactory.getCurrentSession().contains(data);
    }

    @Override
    public void delete(Data date) {
        sessionFactory.getCurrentSession().delete(date);
    }

    @Override
    public void update(Data date) {
        Session session = sessionFactory.getCurrentSession();
        session.update(session.merge(date));
    }
}
