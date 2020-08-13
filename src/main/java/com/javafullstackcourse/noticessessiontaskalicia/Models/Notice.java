package com.javafullstackcourse.noticessessiontaskalicia.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="notices")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String content;

    @Column(nullable = false)
    public Date published;

    @ManyToOne
    public User user;
}
