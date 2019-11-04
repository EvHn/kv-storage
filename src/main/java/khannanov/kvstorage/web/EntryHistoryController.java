package khannanov.kvstorage.web;

import khannanov.kvstorage.data.Entry;
import khannanov.kvstorage.data.EntryHistory;
import khannanov.kvstorage.data.Key;
import khannanov.kvstorage.impl.StateServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class EntryHistoryController {
    private StateServiceImpl stateManager = StateServiceImpl.getInstance();

    @GetMapping("/entry")
    public String setHistory(Model model) {
        model.addAttribute("entryHistory",
                new EntryHistory( stateManager.getHistory()));
        model.addAttribute("entry", new Entry());
        return "entryHistory";
    }

    @PostMapping
    public String getHistory(Key key) {
        return "redirect:/history/entry";
    }
}
