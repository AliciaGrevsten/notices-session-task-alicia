package com.javafullstackcourse.noticessessiontaskalicia.Controllers;

import com.javafullstackcourse.noticessessiontaskalicia.Models.AppUser;
import com.javafullstackcourse.noticessessiontaskalicia.Models.Notice;
import com.javafullstackcourse.noticessessiontaskalicia.Services.AppUserService;
import com.javafullstackcourse.noticessessiontaskalicia.Services.NoticeService;
import com.javafullstackcourse.noticessessiontaskalicia.Utilities.SessionKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public void addNotice(HttpServletResponse response, @RequestParam String title, String content, HttpSession session) throws IOException {
        if(SessionKeeper.getInstance().checkSession(session.getId())) {
            AppUser user = appUserService.getUserByUsername(SessionKeeper.getInstance().getUserSession().appUserUsername);
            Notice notice = new Notice();
            notice.title = title;
            notice.content = content;
            notice.published = new Date();
            notice.appUser = user;

            noticeService.addNotice(notice);
            response.sendRedirect("/");
        }else {
            response.sendRedirect("loginPage");
        }
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public void setNotice(HttpServletResponse response, @RequestParam int id, String title, String content, Model model, HttpSession session) throws IOException {
        if (SessionKeeper.getInstance().checkSession(session.getId())) {
            Notice notice = noticeService.getNotice(id);
            AppUser user = appUserService.getUserByUsername(SessionKeeper.getInstance().getUserSession().appUserUsername);
            if (notice.appUser.id.equals(user.id)) {
                notice.title = title;
                notice.content = content;

                noticeService.addNotice(notice);
                response.sendRedirect("/");
            } else {
                model.addAttribute("message", "You can only edit your own notices");
                response.sendRedirect("/editNotice?id=" + id);
            }
        }else {
            response.sendRedirect("loginPage");
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public void deleteNotice(HttpServletResponse response, @RequestParam int id, Model model, HttpSession session) throws IOException {
        if (SessionKeeper.getInstance().checkSession(session.getId())) {
            AppUser user = appUserService.getUserByUsername(SessionKeeper.getInstance().getUserSession().appUserUsername);
            if (noticeService.getNotice(id).appUser.id.equals(user.id)) {
                noticeService.deleteNotice(id);
                response.sendRedirect("/");
            } else {
                response.sendRedirect("/editNotice?id=" + id);
            }
        } else {
            response.sendRedirect("loginPage");
        }
    }
}
