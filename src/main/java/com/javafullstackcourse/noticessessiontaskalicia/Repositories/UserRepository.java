package com.javafullstackcourse.noticessessiontaskalicia.Repositories;

import com.javafullstackcourse.noticessessiontaskalicia.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getById(int Id);

}
