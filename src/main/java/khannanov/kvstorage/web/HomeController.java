package khannanov.kvstorage.web;

import khannanov.kvstorage.data.Entry;
import khannanov.kvstorage.impl.IStateService;
import khannanov.kvstorage.impl.StateServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private IStateService stateManager = StateServiceImpl.getInstance();
    @GetMapping("/")
    public String getEntries(Model model) {
        List<Entry> list = new ArrayList<>();
        stateManager.getMap().forEach((key, value) -> list.add(new Entry(key, value)));
        model.addAttribute("entries", list);
        return "home";
    }
}
