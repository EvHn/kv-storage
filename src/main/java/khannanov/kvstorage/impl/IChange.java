package khannanov.kvstorage.impl;

public interface IChange {
    public IState apply(IState state);
}
