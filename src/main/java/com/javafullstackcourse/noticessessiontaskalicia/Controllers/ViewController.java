package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.Notice;
import com.javafullstackcourse.noticessessiontaskalicia.Services.CommentService;
import com.javafullstackcourse.noticessessiontaskalicia.Services.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/login")
    private String login() {
        return "login";
    }

    @GetMapping("/editNotice")
    private String editNotice(@RequestParam int id, Model model) {
        Notice notice = noticeService.getNotice(id);
        model.addAttribute(notice);
        return "editNotice";
    }

    @GetMapping("/showComments")
    private String showComments(@RequestParam int id, Model model) {
        model.addAttribute("comments", commentService.getAllCommentsByNoticeId(id));
        model.addAttribute("notice", noticeService.getNotice(id));
        return "showComments";
    }
}
