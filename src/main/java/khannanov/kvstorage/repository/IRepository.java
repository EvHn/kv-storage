package khannanov.kvstorage.repository;


public interface IRepository<Data> {
    void add(Data data);
    void delete(Data data);
    void edit(Data data);
    Data getById(int id);
}
