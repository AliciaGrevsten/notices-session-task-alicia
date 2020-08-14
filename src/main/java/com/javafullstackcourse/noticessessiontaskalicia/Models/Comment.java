package com.javafullstackcourse.noticessessiontaskalicia.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false)
    public String content;

    @Column(nullable = false)
    public Date published;

    @ManyToOne
    public AppUser appUser;

    @ManyToOne
    public Notice notice;
}
