package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.Comment;
import com.javafullstackcourse.noticessessiontaskalicia.Services.CommentService;
import com.javafullstackcourse.noticessessiontaskalicia.Services.NoticeService;
import com.javafullstackcourse.noticessessiontaskalicia.Services.UserService;
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

    // REMOVE LATER
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/comment", method = {RequestMethod.POST, RequestMethod.GET})
    public void addComment(HttpServletResponse response, @RequestParam int id, String content, HttpSession session) throws IOException {
        Comment comment = new Comment();
        comment.notice = noticeService.getNotice(id);
        comment.content = content;
        comment.published = new Date();
        comment.user = userService.getUser(2);

        commentService.addComment(comment);
        response.sendRedirect("/showComments?id=" + id);
    }
}
