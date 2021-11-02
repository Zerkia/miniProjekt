package miniprojekt.web;

import miniprojekt.domain.MiniProjektException;
import miniprojekt.repositories.UserRepositoryImplemented;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import miniprojekt.domain.models.User;
import miniprojekt.domain.services.UserService;
import miniprojekt.repositories.UserRepository;

@Controller
public class MyController {
    private UserService userService = new UserService(new UserRepositoryImplemented());

    private UserRepository repo;
    public MyController() { repo = new UserRepositoryImplemented(); }


    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/register")
    public String register() { return "register"; }

    @PostMapping("/createUser")
    public String createUser(WebRequest request) throws MiniProjektException {
        String username = request.getParameter("username");
        String password1 = request.getParameter("password");
        String password2 = request.getParameter("password2");

        if(password1.equals(password2)){
            User user = userService.createUser(username, password1);
            request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
            return "redirect:/login";
        }
        else{
            throw new MiniProjektException("wrong");
        }
    }



    @GetMapping("/myPage")
    public String myPageUser(Model model, User user){
        model.addAttribute("wishlist", repo.fetchWishList(user));
        return "myPage";
    }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws MiniProjektException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);

        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);

        return "redirect:/myPage";
    }
}
