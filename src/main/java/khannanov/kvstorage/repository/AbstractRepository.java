package khannanov.kvstorage.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractRepository<Data> implements IRepository<Data> {

    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Data data) {
        sessionFactory.getCurrentSession().save(data);
    }

    @Override
    public void saveOrUpdate(Data date) {
        sessionFactory.getCurrentSession().saveOrUpdate(date);
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
