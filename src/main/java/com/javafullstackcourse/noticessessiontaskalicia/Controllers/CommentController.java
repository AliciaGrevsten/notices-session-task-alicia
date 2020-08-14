package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.AppUser;
import com.javafullstackcourse.noticessessiontaskalicia.Models.Comment;
import com.javafullstackcourse.noticessessiontaskalicia.Services.AppUserService;
import com.javafullstackcourse.noticessessiontaskalicia.Services.CommentService;
import com.javafullstackcourse.noticessessiontaskalicia.Services.NoticeService;
import com.javafullstackcourse.noticessessiontaskalicia.Utilities.SessionKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "/comment", method = {RequestMethod.POST, RequestMethod.GET})
    public void addComment(HttpServletResponse response, @RequestParam int id, String content, HttpSession session) throws IOException {
        if (SessionKeeper.getInstance().checkSession(session.getId())) {
            AppUser user = appUserService.getUserByUsername(SessionKeeper.getInstance().getUserSession().appUserUsername);
            Comment comment = new Comment();
            comment.notice = noticeService.getNotice(id);
            comment.content = content;
            comment.published = new Date();
            comment.appUser = user;

            commentService.addComment(comment);
            response.sendRedirect("/showComments?id=" + id);
        } else {
            response.sendRedirect("loginPage");
        }
    }
}
