package khannanov.kvstorage.repository;

import khannanov.kvstorage.data.EntryChange;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EntryChangeRepository implements IRepository<EntryChange> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory =sessionFactory;
    }

    @Override
    public void add(EntryChange entryChange) {

    }

    @Override
    public void delete(EntryChange entryChange) {

    }

    @Override
    public void edit(EntryChange entryChange) {

    }

    @Override
    public EntryChange getById(int id) {
        return null;
    }
}
