package com.javafullstackcourse.noticessessiontaskalicia.Repositories;

import com.javafullstackcourse.noticessessiontaskalicia.Models.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Notice getById(int Id);
}
