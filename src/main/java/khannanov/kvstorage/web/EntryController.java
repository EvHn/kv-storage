package khannanov.kvstorage.web;

import khannanov.kvstorage.model.Entry;

import khannanov.kvstorage.service.IStorageService;
import khannanov.kvstorage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/newEntry")
public class EntryController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EntryController.class);

    @Autowired
    private IStorageService storageService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public String createState(Model model) {
        model.addAttribute("entry", new Entry());
        return "entry";
    }

    @PostMapping
    public String setEntry(Entry entry, Principal principal) {
        entry.setUser(userService.getUserByUsername(principal.getName()));
        storageService.add(entry);
        return "redirect:/home";
    }


}
