package com.javafullstackcourse.noticessessiontaskalicia.Models;

import javax.persistence.*;

@Entity
@Table(name="appusers")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(unique = true, nullable = false)
    public String appUserUsername;

    @Column(nullable = false)
    public String appUserPassword;

    public String getAppUserUsername(){ return appUserUsername; }

    public void setAppUserUsername(String username) {
        this.appUserUsername = username;
    }

    public void setAppUserPassword(String password) {
        this.appUserPassword = password;
    }

    public String getAppUserPassword(){ return appUserPassword; }
}
