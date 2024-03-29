package khannanov.kvstorage.web;

import khannanov.kvstorage.service.IUserService;
import khannanov.kvstorage.web.model.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("registration", new Registration());
        return "register";
    }

    @PostMapping
    public String getLogin(Registration registration) {
        userService.addUser(registration.toUser(passwordEncoder));
        return "redirect:/login";
    }
}