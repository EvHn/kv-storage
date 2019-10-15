package khannanov.kvstorage.data;

public class StateManager {
    private State state;
    private History history;

    public StateManager set(Integer number, Line line) {
        state.set(number, line);
        return this;
    }

    public StateManager insert(Integer afterLine, Line line) {
        state.insert(afterLine, line);
        return this;
    }

    public StateManager commit() {
        history.commit(state);
        state = State.copy(state);
        return this;
    }
}
