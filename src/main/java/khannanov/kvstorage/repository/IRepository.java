package khannanov.kvstorage.repository;


public interface IRepository<Data> {
    void save(Data data);
    void saveOrUpdate(Data data);
    void delete(Data data);
    void edit(Data data);
}
