package miniprojekt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import miniprojekt.domain.models.User;
import miniprojekt.domain.services.UserService;

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

    @GetMapping("/myPage")
    public String myPage(){ return "myPage"; }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws miniProjektException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);

        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);

        return "redirect:/myPage";
    }
}
