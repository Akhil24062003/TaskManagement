package com.SprintProject.TaskManagement.service;

import com.SprintProject.TaskManagement.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);
    List<Comment> getAllComments();
    Comment getCommentById(Integer id);
    Comment updateComment(Integer id, Comment comment);
    void deleteComment(Integer id);
}