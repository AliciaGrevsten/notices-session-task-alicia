package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.AppUser;
import com.javafullstackcourse.noticessessiontaskalicia.Models.CommonResponse;
import com.javafullstackcourse.noticessessiontaskalicia.Services.AppUserService;
import com.javafullstackcourse.noticessessiontaskalicia.Utilities.SessionKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/login")
    public void login(@ModelAttribute("user") AppUser user, HttpServletResponse response, HttpSession session) throws IOException {
        List<AppUser> allUsers = appUserService.getAllUsers();

        boolean validLogin = allUsers
                .stream()
                .anyMatch(x -> x.getAppUserUsername().equals(user.getAppUserUsername()) && x.getAppUserPassword().equals(user.getAppUserPassword()));

        if(validLogin) {
            SessionKeeper.getInstance().addSession(session.getId());
            SessionKeeper.getInstance().addUserSession(user);
        }
        response.sendRedirect("/");
    }

    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        SessionKeeper.getInstance().removeSession(session.getId());
        SessionKeeper.getInstance().removeUserSession(SessionKeeper.getInstance().getUserSession());
        response.sendRedirect("/");
    }

    @PostMapping ("/register")
    public void register(@ModelAttribute("user") AppUser user, HttpServletResponse response, Model model) throws IOException {
        List<AppUser> allUsers = appUserService.getAllUsers();

        boolean userExists = allUsers
                .stream()
                .anyMatch(x -> x.getAppUserUsername().equals(user.getAppUserUsername()));

        if (!userExists) {
            appUserService.addUser(user);
            response.sendRedirect("loginPage");
        } else {
            model.addAttribute("message", "User already exists.");
            response.sendRedirect("registerPage");
        }
    }
}
