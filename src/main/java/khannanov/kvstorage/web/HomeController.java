package khannanov.kvstorage.web;

import khannanov.kvstorage.data.StateManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    public static StateManager stateManager = new StateManager();
    @GetMapping("/")
    public String getEntries(Model model) {
        model.addAttribute("entries", stateManager.getEntryList());
        return "home";
    }
}
