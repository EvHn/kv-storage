package khannanov.kvstorage.data;

import khannanov.kvstorage.data.simple.SimpleDataFactory;
import lombok.Getter;
import org.apache.catalina.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class StateManager {

    private Map<String, History> map = new HashMap<>();
    private IDiffer differ;
    private IDataFactory dataFactory = new SimpleDataFactory();

    public void add(Entry entry) {
        String key = entry.getKey();
        String state = entry.getValue();
        if(map.containsKey(key)) {
            map.get(key).setState(state);
        } else {
            map.put(key, new History(dataFactory.createState(state)));
        }
    }

    public List<Entry> getEntryList() {
        List<Entry> list = new ArrayList<>();
        map.forEach((key, history) -> list.add(new Entry(key, history.state.get())));
        return list;
    }

    private class History {
        private IState state;
        private IState currState;
        List<IChange> changes = new ArrayList<>();

        public History(IState state) {
            this.state = state;
        }

        public void setState(String state) {
            currState = dataFactory.createState(state);
        }

        public String getState() {
            return state.get();
        }

        public String getCurrState() {
            return currState.get();
        }

        public void commit() {
            if (!state.equals(currState)) {
                changes.add(differ.calc(state, currState));
                state = currState;
            }
        }
    }
}
