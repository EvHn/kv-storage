package khannanov.kvstorage.web;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryHistory;
import khannanov.kvstorage.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class EntryHistoryController {

    @Autowired
    private IStorageService storageService;

    @GetMapping("/{key}")
    public String setHistory(Model model, @PathVariable("key") String key) {
        model.addAttribute("entryHistory", storageService.getHistory(key));
        model.addAttribute("entry", storageService.getEntry(key));
        return "entryHistory";
    }

    @GetMapping("/")
    public String setHistory(Model model) {
        model.addAttribute("entryHistory", new EntryHistory());
        model.addAttribute("entry", new Entry());
        return "entryHistory";
    }
}
