package miniprojekt.web;

import miniprojekt.domain.MiniProjektException;
import miniprojekt.domain.models.Wishlist;
import miniprojekt.repositories.UserRepositoryImplemented;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import miniprojekt.domain.models.User;
import miniprojekt.domain.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class MyController {
    private UserService userService = new UserService(new UserRepositoryImplemented());

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

    @GetMapping("/addItem")
    public String addItem() { return "addItem"; }

    @PostMapping("/addItemUser")
    public String addItemUser(String itemName, int itemQuantity, WebRequest request) throws MiniProjektException {
        User user = (User) request.getAttribute("user",1);
        userService.addItem(itemName, itemQuantity, user);
        return "redirect:myPage";
    }

    @GetMapping("/myPage")
    public String myPageUser(Model model, WebRequest request){
        User user = (User) request.getAttribute("user",1);
        // = Integer.parseInt(request.getParameter("userID"));
        model.addAttribute("wishlists", userService.fetchWishList(user));
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


        return "redirect:myPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }



}
