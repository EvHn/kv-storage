package khannanov.kvstorage.data;

import lombok.Data;

import java.util.*;

@Data
public class Value {
    private final List<State> valueVersionList;

    public static Value createValue(State lines)
    {
        return new Value(lines);
    }

    private Value(State state) {
        valueVersionList = new ArrayList<>();
        commit(state);
    }

    public void commit(State state) {
        valueVersionList.add(state);
    }


}
