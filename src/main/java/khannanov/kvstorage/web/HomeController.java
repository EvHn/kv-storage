package khannanov.kvstorage.web;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.User;
import khannanov.kvstorage.service.IStorageService;
import khannanov.kvstorage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IStorageService storageService;
    @Autowired
    private IUserService userService;

    @GetMapping
    public String getEntries(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Iterable<Entry> entries =  storageService.getEntries(user);
        model.addAttribute("entries", entries);
        return "home";
    }
}
