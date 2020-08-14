package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.Notice;
import com.javafullstackcourse.noticessessiontaskalicia.Services.NoticeService;
import com.javafullstackcourse.noticessessiontaskalicia.Utilities.SessionKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.GET})
    public void addNotice(HttpServletResponse response, @RequestParam String title, String content) throws IOException {
        Notice notice = new Notice();
        notice.title = title;
        notice.content = content;
        notice.published = new Date();
        notice.appUser = SessionKeeper.getInstance().getUserSession();

        noticeService.addNotice(notice);
        response.sendRedirect("/");
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public void setNotice(HttpServletResponse response, @RequestParam int id, String title, String content) throws IOException {
        Notice notice = noticeService.getNotice(id);
        notice.title = title;
        notice.content = content;

        noticeService.addNotice(notice);
        response.sendRedirect("/");
    }


    public void deleteNotice(HttpServletResponse response, @RequestParam int id) throws IOException {
        noticeService.deleteNotice(id);
        response.sendRedirect("/");
    }


}
