package khannanov.kvstorage.impl.simple;

import khannanov.kvstorage.impl.IChange;
import khannanov.kvstorage.impl.IDiffer;
import khannanov.kvstorage.impl.IState;

public class SimpleDiffer implements IDiffer {

    @Override
    public IChange calc(IState oldState, IState newState) {
        return new SimpleChange(newState);
    }
}
