package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.Notice;
import com.javafullstackcourse.noticessessiontaskalicia.Services.NoticeService;
import com.javafullstackcourse.noticessessiontaskalicia.Utilities.SessionKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public void setNotice(HttpServletResponse response, @RequestParam int id, String title, String content, Model model) throws IOException {
        Notice notice = noticeService.getNotice(id);
        if (notice.appUser.id == SessionKeeper.getInstance().getUserSession().id) {
            notice.title = title;
            notice.content = content;

            noticeService.addNotice(notice);
            response.sendRedirect("/");
        } else {
            model.addAttribute("message", "You can only edit your own notices");
            response.sendRedirect("/editNotice");
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    public void deleteNotice(HttpServletResponse response, @RequestParam int id, Model model) throws IOException {
        if (noticeService.getNotice(id).appUser.id == SessionKeeper.getInstance().getUserSession().id) {
            noticeService.deleteNotice(id);
            response.sendRedirect("/");
        } else {
            model.addAttribute("message", "You can only delete your own notices");
            response.sendRedirect("/editNotice");
        }
    }
}
