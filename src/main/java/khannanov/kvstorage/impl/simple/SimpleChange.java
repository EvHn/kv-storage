package khannanov.kvstorage.impl.simple;

import khannanov.kvstorage.impl.IChange;
import khannanov.kvstorage.impl.IState;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SimpleChange implements IChange {
    private final IState prevState;

    @Override
    public IState apply(IState state) {
        return prevState;
    }
}
