package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.*;
import com.javafullstackcourse.noticessessiontaskalicia.Services.*;
import com.javafullstackcourse.noticessessiontaskalicia.Utilities.SessionKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/addNotice")
    private String addNotice() {
        return "addNotice";
    }

    @GetMapping("/loginPage")
    private String login(Model model) {
        model.addAttribute("user", new AppUser());
        return "loginPage";
    }

    @GetMapping("/registerPage")
    private String register(Model model) {
        model.addAttribute("user", new AppUser());
        model.addAttribute("message", "");
        return "registerPage";
    }

    @GetMapping("/")
    public String listNotices(HttpServletResponse response, HttpSession session, Model model){
        model.addAttribute("user", new AppUser());
        if(checkUserSession(session.getId())) {
            model.addAttribute("notices", noticeService.getAllNotices());
            return "private";
        } else {
            model.addAttribute("notices", noticeService.getAllNotices());
            return "public";
        }
    }

    @GetMapping("/editNotice")
    private String editNotice(@RequestParam int id, Model model) {
        Notice notice = noticeService.getNotice(id);
        model.addAttribute(notice);
        return "editNotice";
    }

    @GetMapping("/showComments")
    private String showComments(HttpSession session, @RequestParam int id, Model model) {
        model.addAttribute("user", new AppUser());
        model.addAttribute("comments", commentService.getAllCommentsByNoticeId(id));
        model.addAttribute("notice", noticeService.getNotice(id));
        if(checkUserSession(session.getId())) {
            return "privateComments";
        } else {
            return "publicComments";
        }
    }

    private boolean checkUserSession(String sessionId) {
        return SessionKeeper.getInstance().checkSession(sessionId);
    }
}
