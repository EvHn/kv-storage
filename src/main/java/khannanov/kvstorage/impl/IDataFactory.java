package khannanov.kvstorage.impl;

public interface IDataFactory {
    public IState createState(String string);
    public IDiffer createDiffer();
}
