package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.User;
import sk.tuke.gamestudio.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UserController {
    private User loggedUser;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(String login, String passwd, HttpSession session) {
        User user = userService.getUserByUsername(login);
        if (user != null && user.getPassword().equals(passwd)) {
            loggedUser = user;
            session.setAttribute("loggedUser", loggedUser);
            session.removeAttribute("loginError");
            return "redirect:/";
        }
        session.setAttribute("loginError", true);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        loggedUser = null;
        session.removeAttribute("loggedUser");
        return "redirect:/";
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isLogged() {
        return loggedUser != null;
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, HttpSession session) {
        try {
            userService.registerUser(user);
            loggedUser = user;
            session.setAttribute("loggedUser", loggedUser);
            return "redirect:/";
        } catch (Exception e) {
            // You can handle any exception that may occur during registration, such as duplicate usernames
            return "redirect:/register?error";
        }
    }


}


/*
package sk.tuke.gamestudio.server.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.User;

import javax.servlet.http.HttpSession;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UserController {
    private User loggedUser;

    */
/*@PostMapping("/login")
    public String login(String login, String passwd) {
        if ("heslo".equals(passwd)) {
            loggedUser = new User(login);
            return "redirect:/pexeso/new";
        }

        return "redirect:/";
    }*//*

    @PostMapping("/login")
    public String login(String login, String passwd, HttpSession session) {
        if ("heslo".equals(passwd)) {
            loggedUser = new User(login);
            session.setAttribute("loggedUser", loggedUser);
            return "redirect:/";
        }

        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout() {
        loggedUser = null;
        return "redirect:/";
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isLogged() {
        return loggedUser != null;
    }
}
*/
