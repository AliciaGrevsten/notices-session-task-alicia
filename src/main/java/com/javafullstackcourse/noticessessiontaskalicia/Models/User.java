package com.javafullstackcourse.noticessessiontaskalicia.Models;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(unique = true, nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;
}
