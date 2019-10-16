package khannanov.kvstorage.data;

public interface IChange {
    public IState apply(IState state);
}
