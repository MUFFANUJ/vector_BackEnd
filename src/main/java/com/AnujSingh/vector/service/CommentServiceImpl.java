package com.AnujSingh.vector.service;

import com.AnujSingh.vector.model.Comment;
import com.AnujSingh.vector.model.Issue;
import com.AnujSingh.vector.model.User;
import com.AnujSingh.vector.repository.CommentRepository;
import com.AnujSingh.vector.repository.IssueRepository;
import com.AnujSingh.vector.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;


    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalIssue.isEmpty()){
            throw new Exception("Issue not found");
        }
        if(optionalUser.isEmpty()){
            throw new Exception("User not found");
        }
        Issue issue = optionalIssue.get();
        User user = optionalUser.get();

        Comment comment = new Comment();
        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepository.save(comment);

        issue.getComments().add(savedComment);
        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalComment.isEmpty()){
            throw new Exception("Comment not found with the id is" + commentId);
        }
        if(optionalUser.isEmpty()){
            throw new Exception("User not found with the id is" + commentId);
        }

        Comment comment = optionalComment.get();
        User user = optionalUser.get();
        if (comment.getUser().equals(user)){
            commentRepository.delete(comment);
        }else{
            throw new Exception("user Does not have permission to delete the comment");
        }
    }

    @Override
    public List<Comment> findCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }
}
