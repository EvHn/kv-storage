package khannanov.kvstorage.web;

import khannanov.kvstorage.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private IStorageService storageService;

    @Autowired
    public void setStorageService(IStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String getEntries(Model model) {
        model.addAttribute("entries", storageService.getEntries());
        return "home";
    }
}
