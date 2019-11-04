package khannanov.kvstorage.impl;

import java.util.List;
import java.util.Map;

public interface IStateService {
    List<String> getHistory(String key);
    void add(String key,  String value);
    void delete(String key);
    Map<String, String> getMap();
}
