package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.Comment;
import com.javafullstackcourse.noticessessiontaskalicia.Models.Notice;
import com.javafullstackcourse.noticessessiontaskalicia.Services.CommentService;
import com.javafullstackcourse.noticessessiontaskalicia.Services.NoticeService;
import com.javafullstackcourse.noticessessiontaskalicia.Services.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void addComment(HttpServletResponse response, @RequestParam int id, String content) throws IOException {
        Comment comment = new Comment();
        comment.notice = noticeService.getNotice(id);
        comment.content = content;
        comment.published = new Date();
        comment.user = userService.getUser(2);

        commentService.addComment(comment);
        response.sendRedirect("/showComments");
    }
}
