package com.javafullstackcourse.noticessessiontaskalicia.Seeders;

import com.javafullstackcourse.noticessessiontaskalicia.Models.*;
import com.javafullstackcourse.noticessessiontaskalicia.Services.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;

public class Seeder {
    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CommentService commentService;

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Notice> notices = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();

    public Seeder() {
        createUsers();
        createNotices();
        createComments();
        seedData();
    }

    public void createUsers() {
        User user1 = new User();
        user1.id = 1;
        user1.username = "aquelin";
        user1.password = "password123";

        User user2 = new User();
        user2.id = 2;
        user2.username = "pelle";
        user2.password = "password123";

        users.add(user1);
        users.add(user2);
    }

    public void createNotices() {
        Notice notice1 = new Notice();
        notice1.id = 1;
        notice1.title = "Web Page Is Running!";
        notice1.content = "We got the web page up and running! Excited to see whats to come.";
        notice1.published = new Date();
        notice1.user = users.get(0);

        Notice notice2 = new Notice();
        notice2.id = 2;
        notice2.title = "Database is up and seeding!";
        notice2.content = "Through Hibernate we managed to deploy our database and seed our initial data. This notice is seeded!";
        notice2.published = new Date();
        notice2.user = users.get(0);

        Notice notice3 = new Notice();
        notice3.id = 3;
        notice3.title = "New User!";
        notice3.content = "Hello! My name is Pelle and I am seeded user just like Aquelin. Nice to meet you!";
        notice3.published = new Date();
        notice3.user = users.get(1);

        notices.add(notice1);
        notices.add(notice2);
        notices.add(notice3);
    }

    public void createComments() {
        Comment comment1 = new Comment();
        comment1.id = 1;
        comment1.content = "Great job!";
        comment1.published = new Date();
        comment1.notice = notices.get(1);
        comment1.user = users.get(1);

        Comment comment2 = new Comment();
        comment2.id = 2;
        comment2.content = "Welcome To the Team Pelle!";
        comment2.published = new Date();
        comment2.notice = notices.get(2);
        comment2.user = users.get(0);

        Comment comment3 = new Comment();
        comment3.id = 3;
        comment3.content = "It is nice to be onboard!";
        comment3.published = new Date();
        comment3.notice = notices.get(2);
        comment3.user = users.get(1);

        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
    }

    public void seedData() {
        for (User user : users) {
            userService.addUser(user);
        }

        for (Notice notice : notices) {
            noticeService.addNotice(notice);
        }

        for (Comment comment : comments) {
            commentService.addComment(comment);
        }
    }
}
