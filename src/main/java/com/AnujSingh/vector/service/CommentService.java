package com.AnujSingh.vector.service;

import com.AnujSingh.vector.model.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long issueId,Long userId,String comment) throws Exception;
    void deleteComment(Long commentId,Long userId) throws Exception;

    List<Comment> findCommentsByIssueId(Long issueId);
}
