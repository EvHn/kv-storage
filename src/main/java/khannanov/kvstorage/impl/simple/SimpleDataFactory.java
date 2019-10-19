package khannanov.kvstorage.impl.simple;

import khannanov.kvstorage.impl.IDataFactory;
import khannanov.kvstorage.impl.IDiffer;
import khannanov.kvstorage.impl.IState;

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
