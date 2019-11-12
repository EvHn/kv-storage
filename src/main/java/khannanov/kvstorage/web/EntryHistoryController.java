package khannanov.kvstorage.web;

import khannanov.kvstorage.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/history")
public class EntryHistoryController {
    private IStorageService storageService;

    @Autowired
    public void setStorageService(IStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/entry/{key}")
    public ModelAndView setHistory(@PathVariable("key") String key) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("entryHistory");
        modelAndView.addObject("entryHistory", storageService.getHistory(key));
        modelAndView.addObject("entry", storageService.getEntry(key));
        return modelAndView;
    }
}
