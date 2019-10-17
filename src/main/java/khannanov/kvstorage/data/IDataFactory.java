package khannanov.kvstorage.data;

import khannanov.kvstorage.data.IState;

public interface IDataFactory {
    public IState createState(String string);
    public IDiffer createDiffer();
}
