package com.javafullstackcourse.noticessessiontaskalicia.Repositories;

import com.javafullstackcourse.noticessessiontaskalicia.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser getById(int Id);

    AppUser getAppUserByAppUserUsername(String username);
}
