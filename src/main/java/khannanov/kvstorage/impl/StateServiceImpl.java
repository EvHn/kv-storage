package khannanov.kvstorage.impl;

import khannanov.kvstorage.impl.simple.SimpleDataFactory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class StateServiceImpl implements IStateService {

    private Map<String, History> map = new HashMap<>();
    private khannanov.kvstorage.impl.IDiffer differ;
    private khannanov.kvstorage.impl.IDataFactory dataFactory = new SimpleDataFactory();
    private static volatile StateServiceImpl instance;

    public static StateServiceImpl getInstance() {
        StateServiceImpl localeInstance = instance;
        if(localeInstance == null) {
            synchronized (StateServiceImpl.class) {
                localeInstance = instance;
                if(localeInstance == null) {
                    instance = localeInstance = new StateServiceImpl();
                }
            }
        }
        return localeInstance;
    }

    private StateServiceImpl() {
        differ = dataFactory.createDiffer();
    }

    @Override
    public void add(String key,  String value) {
        if(map.containsKey(key)) {
            History history = map.get(key);
            history.setState(value);
            history.commit();
        } else {
            map.put(key, new History(dataFactory.createState(value)));
        }
    }

    @Override
    public List<String> getHistory(String key) {
        if(map.containsKey(key)) {
            return map.get(key).get();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void delete(String key) {
        throw new RuntimeException("No supported yet");
    }

    @Override
    public Map<String, String> getMap() {
        Map<String, String> strMap = new HashMap<>();
        map.forEach((s, history) -> strMap.put(s, history.getState()));
        return strMap;
    }

    private class History {
        private khannanov.kvstorage.impl.IState state;
        private khannanov.kvstorage.impl.IState currState;
        List<khannanov.kvstorage.impl.IChange> changes = new ArrayList<>();

        public History(khannanov.kvstorage.impl.IState state) {
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
            khannanov.kvstorage.impl.IState localState = state;
            for(khannanov.kvstorage.impl.IChange ch : changes) {
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
