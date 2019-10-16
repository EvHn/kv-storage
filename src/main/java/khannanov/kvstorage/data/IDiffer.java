package khannanov.kvstorage.data;

public interface IDiffer {
    public IChange calc(IState oldState, IState newState);
}
