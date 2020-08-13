package com.javafullstackcourse.noticessessiontaskalicia.Repositories;

import com.javafullstackcourse.noticessessiontaskalicia.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment getById(int Id);

    List<Comment> findCommentsByNotice_Id(int id);
}
