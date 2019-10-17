package khannanov.kvstorage.data.simple;

import khannanov.kvstorage.data.IState;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleState implements IState {
    private String state;

    @Override
    public String get() {
        return state;
    }

    @Override
    public void set(String state) {
        this.state = state;
    }
}
