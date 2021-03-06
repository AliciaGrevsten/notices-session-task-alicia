package com.javafullstackcourse.noticessessiontaskalicia.Services;

import com.javafullstackcourse.noticessessiontaskalicia.Models.Notice;
import com.javafullstackcourse.noticessessiontaskalicia.Repositories.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class NoticeService {

    @Autowired
    DataSource dataSource;

    @Autowired
    NoticeRepository noticeRepository;

    public NoticeService(){}

    public Notice getNotice(int id) {
        return noticeRepository.findById(id).get();
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "published"));
    }

    public void deleteNotice(int id) {
        noticeRepository.deleteById(id);
    }

    public void addNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    public void updateNotice(Notice notice) {
        noticeRepository.save(notice);
    }
}
