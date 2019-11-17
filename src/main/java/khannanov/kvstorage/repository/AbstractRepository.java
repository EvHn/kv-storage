package khannanov.kvstorage.repository;

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
    public void delete(Data date) {
        sessionFactory.getCurrentSession().delete(date);
    }

    @Override
    public void edit(Data date) {
        sessionFactory.getCurrentSession().update(date);
    }
}
