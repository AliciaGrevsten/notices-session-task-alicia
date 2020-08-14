package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.User;
import com.javafullstackcourse.noticessessiontaskalicia.Services.UserService;
import com.javafullstackcourse.noticessessiontaskalicia.Utilities.SessionKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public void login(@ModelAttribute("user")User user, HttpServletResponse response, HttpSession session) throws IOException {
        List<User> allUsers = userService.getAllUsers();

        boolean validLogin = allUsers
                .stream()
                .anyMatch(x -> x.getUsername().equals(user.getUsername()) && x.getPassword().equals(user.getPassword()));

        if(validLogin) {
            SessionKeeper.getInstance().addSession(session.getId());
        }

        response.sendRedirect("/");
    }

    @PostMapping ("/register")
    public void register() {

    }
}
