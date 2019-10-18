package khannanov.kvstorage.web;


import khannanov.kvstorage.data.Entry;
import khannanov.kvstorage.data.StateManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EntryController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EntryController.class);
    private StateManager stateManager = StateManager.getInstance();

    @GetMapping("/entry")
    public String createState(Model model) {
        model.addAttribute("entry", new Entry());
        return "entry";
    }

    @PostMapping
    public String setEntry(Entry entry) {
        stateManager.add(entry);
        return "redirect:/";
    }
}
