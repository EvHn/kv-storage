package khannanov.kvstorage.impl;

import khannanov.kvstorage.data.Entry;
import khannanov.kvstorage.data.EntryHistory;
import khannanov.kvstorage.impl.simple.SimpleDataFactory;
import lombok.Getter;

import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class StateManager {

    private Map<String, History> map = new HashMap<>();
    private IDiffer differ;
    private IDataFactory dataFactory = new SimpleDataFactory();
    private static volatile StateManager instance;

    public static StateManager getInstance() {
        StateManager localeInstance = instance;
        if(localeInstance == null) {
            synchronized (StateManager.class) {
                localeInstance = instance;
                if(localeInstance == null) {
                    instance = localeInstance = new StateManager();
                }
            }
        }
        return localeInstance;
    }

    private StateManager() {
        differ = dataFactory.createDiffer();
    }

    public void add(String key,  String value) {
        if(map.containsKey(key)) {
            History history = map.get(key);
            history.setState(value);
            history.commit();
        } else {
            map.put(key, new History(dataFactory.createState(value)));
        }
    }

    public List<String> getHistory(String key) {
        return map.get(key).get();
    }

    public Map<String, String> getMap() {
        Map<String, String> strMap = new HashMap<>();
        map.forEach((s, history) -> strMap.put(s, history.getState()));
        return strMap;
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

        public List<String> get() {
            List<String> strings = new ArrayList<>();
            IState localState = state;
            for(IChange ch : changes) {
                strings.add(localState.get());
                localState = ch.apply(state);
            }
            return strings;
        }

        public void commit() {
            if (!state.equals(currState)) {
                changes.add(differ.calc(state, currState));
                state = currState;
            }
        }
    }
}
