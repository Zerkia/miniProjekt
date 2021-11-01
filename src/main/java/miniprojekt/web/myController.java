package miniprojekt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class myController {

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/register")
    public String register() { return "register"; }

    @PostMapping("/createUser")
    public String createUser() {
        return "";
    }

    @GetMapping("/login")
    public String login(){ return "login"; }
}
