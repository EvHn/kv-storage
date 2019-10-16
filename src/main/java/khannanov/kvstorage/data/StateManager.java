package khannanov.kvstorage.data;

import lombok.Data;

import java.util.List;

@Data
public class StateManager {
    private IState state;
    private IState newState;
    private List<IChange> history;
    private IDiffer differ;

    public StateManager setState(IState state) {
        newState = state;
        return this;
    }

    public StateManager commit() {
        if(state.equals(newState)) {
            history.add(differ.calc(state, newState));
            state = newState;
        }
        return this;
    }
}
