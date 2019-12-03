package khannanov.kvstorage.web;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.User;
import khannanov.kvstorage.service.IStorageService;
import khannanov.kvstorage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IStorageService storageService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public String getEntries(Model model, Principal principal) {
        String username = principal.getName();
        log.info("Send entries to user : {}", username);
        try {
            User user = userService.getUserByUsername(username);
            Iterable<Entry> entries =  storageService.getEntries(user);
            model.addAttribute("entries", entries);
        } catch (UsernameNotFoundException ex) {
            log.error("User {} not found", username);
        }
        return "home";
    }
}
