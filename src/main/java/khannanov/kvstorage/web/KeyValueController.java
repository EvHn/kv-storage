package khannanov.kvstorage.web;

import khannanov.kvstorage.data.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class KeyValueController {

    private Map<String, Value> map = new HashMap<>();

    @GetMapping("/kvmap")
    public String getKVMap()
    {
        return "home";
    }
}
