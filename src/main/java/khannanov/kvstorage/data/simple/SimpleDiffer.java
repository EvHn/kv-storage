package khannanov.kvstorage.data.simple;

import khannanov.kvstorage.data.IChange;
import khannanov.kvstorage.data.IDiffer;
import khannanov.kvstorage.data.IState;

public class SimpleDiffer implements IDiffer {

    @Override
    public IChange calc(IState oldState, IState newState) {
        return new SimpleChange(newState);
    }
}
