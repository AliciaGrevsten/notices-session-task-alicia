package com.javafullstackcourse.noticessessiontaskalicia.Services;

import com.javafullstackcourse.noticessessiontaskalicia.Models.User;
import com.javafullstackcourse.noticessessiontaskalicia.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class UserService {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    public UserService(){}

    public User getUser(int id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "published"));
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
