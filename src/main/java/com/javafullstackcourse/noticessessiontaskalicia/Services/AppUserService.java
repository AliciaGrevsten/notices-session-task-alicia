package com.javafullstackcourse.noticessessiontaskalicia.Services;

import com.javafullstackcourse.noticessessiontaskalicia.Models.AppUser;
import com.javafullstackcourse.noticessessiontaskalicia.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class AppUserService {

    @Autowired
    DataSource dataSource;

    @Autowired
    AppUserRepository appUserRepository;

    public AppUserService(){}

    public AppUser getUser(int id) {
        return appUserRepository.findById(id).get();
    }

    public AppUser getUserByUsername(String username) { return appUserRepository.getAppUserByAppUserUsername(username); }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public void deleteUser(int id) {
        appUserRepository.deleteById(id);
    }

    public void addUser(AppUser user) {
        appUserRepository.save(user);
    }

    public void updateUser(AppUser user) {
        appUserRepository.save(user);
    }
}
