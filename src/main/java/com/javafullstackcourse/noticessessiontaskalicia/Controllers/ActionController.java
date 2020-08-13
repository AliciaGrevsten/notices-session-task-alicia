package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.Comment;
import com.javafullstackcourse.noticessessiontaskalicia.Models.Notice;
import com.javafullstackcourse.noticessessiontaskalicia.Services.CommentService;
import com.javafullstackcourse.noticessessiontaskalicia.Services.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ActionController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/addNotice")
    private String addNotice() {
        return "addNotice";
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
        return "comments";
    }
}
