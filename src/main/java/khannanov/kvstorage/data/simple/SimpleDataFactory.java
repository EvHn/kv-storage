package khannanov.kvstorage.data.simple;

import khannanov.kvstorage.data.IDataFactory;
import khannanov.kvstorage.data.IDiffer;
import khannanov.kvstorage.data.IState;

public class SimpleDataFactory implements IDataFactory {
    @Override
    public IState createState(String string) {
        return new SimpleState(string);
    }

    @Override
    public IDiffer createDiffer() {
        return new SimpleDiffer();
    }
}
