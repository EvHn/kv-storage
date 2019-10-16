package khannanov.kvstorage.data.simple;

import khannanov.kvstorage.data.IChange;
import khannanov.kvstorage.data.IState;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SimpleChange implements IChange {
    private final IState change;

    @Override
    public IState apply(IState state) {
        return change;
    }
}
