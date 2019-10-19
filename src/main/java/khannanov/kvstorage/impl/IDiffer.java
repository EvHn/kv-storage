package khannanov.kvstorage.impl;

public interface IDiffer {
    public IChange calc(IState oldState, IState newState);
}
