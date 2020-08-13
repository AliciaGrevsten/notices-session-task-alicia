package com.javafullstackcourse.noticessessiontaskalicia.Services;

import com.javafullstackcourse.noticessessiontaskalicia.Models.Comment;
import com.javafullstackcourse.noticessessiontaskalicia.Models.User;
import com.javafullstackcourse.noticessessiontaskalicia.Repositories.CommentRepository;
import com.javafullstackcourse.noticessessiontaskalicia.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    DataSource dataSource;

    @Autowired
    CommentRepository commentRepository;

    public CommentService(){}

    public Comment getComment(int id) {
        return commentRepository.findById(id).get();
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll(Sort.by(Sort.Direction.DESC, "published"));
    }
    public List<Comment> getAllCommentsByNoticeId(int id) {
        return commentRepository.findCommentsByNotice_Id(id);
    }

    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }
}
