package khannanov.kvstorage.repository;


public interface IRepository<Data> {
    void save(Data data);
    void delete(Data data);
    boolean exist(Data data);
    void update(Data data);
}
