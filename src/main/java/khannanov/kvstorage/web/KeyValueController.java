package khannanov.kvstorage.web;


import khannanov.kvstorage.data.IState;
import khannanov.kvstorage.data.StateManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class KeyValueController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(KeyValueController.class);
    private Map<String, StateManager> map = new HashMap<>();

    @GetMapping("/getkvmap")
    public String getKVMap(Model model) {
        Map<String, IState> stateMap = new HashMap<>();
        map.forEach((s, stateManager) -> stateMap.put(s, stateManager.getState()));
        log.info("statemap: ", map);
        model.addAttribute("kvmap", stateMap);
        return "home";
    }

    @PostMapping("setState")
    public String setState(Map.Entry<String, IState> entry) {
        map.get(entry.getKey()).setState(entry.getValue()).commit();
        return "home";
    }
}
