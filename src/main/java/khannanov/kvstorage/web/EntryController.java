package khannanov.kvstorage.web;

import khannanov.kvstorage.model.Entry;

import khannanov.kvstorage.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/new")
public class EntryController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EntryController.class);

    @Autowired
    private IStorageService storageService;

    @GetMapping("/entry")
    public String createState(Model model) {
        model.addAttribute("entry", new Entry());
        return "entry";
    }

    @PostMapping
    public String setEntry(Entry entry) {
        storageService.add(entry);
        return "redirect:/";
    }


}
