package khannanov.kvstorage.data;

import lombok.Data;

import java.util.*;

@Data
public class History {
    private final List<State> valueVersionList;

    public static History createHistory(State firstState)
    {
        return new History(firstState);
    }

    private History(State state) {
        valueVersionList = new ArrayList<>();
        commit(state);
    }

    public void commit(State state) {
        valueVersionList.add(state);
    }


}
