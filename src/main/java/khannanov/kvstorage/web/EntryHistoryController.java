package khannanov.kvstorage.web;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.service.IStorageService;
import khannanov.kvstorage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/history")
public class EntryHistoryController {

    @Autowired
    private IStorageService storageService;
    @Autowired
    private IUserService userService;

    @GetMapping
    public String setHistory(Model model, @RequestParam String key, Principal principal) {
        UserDetails user = userService.getUserByUsername(principal.getName());
        Entry entry = storageService.getEntry(user, key);
        model.addAttribute("entry", entry);
        model.addAttribute("entryHistory", storageService.getHistory(entry));
        return "entryHistory";
    }
}
