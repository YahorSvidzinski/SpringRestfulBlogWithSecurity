package com.example.blog.controller;

import com.example.blog.model.Comment;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.security.CurrentUser;
import com.example.blog.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    @PostMapping("/create/{id}")
    @PreAuthorize("hasRole('USER')")
    public Comment createPost(@CurrentUser UserPrincipal currentUser,
                           @Valid @RequestBody Comment comment,@PathVariable("id") Long id) {
        return userRepository.findById(currentUser.getId()).map(user -> {
            comment.setUser(user);
            comment.setPost(postRepository.findPostById(id));
            return commentRepository.save(comment);
        }).orElseThrow(() -> new com.example.blog.exception.ResourceNotFoundException("Comment id", "not found", "id"));
    }
    @GetMapping("/post/{id}/comments")
    @PreAuthorize("hasRole('USER')")
    public Optional<Comment> getComment(@CurrentUser UserPrincipal currentUser
                                     , @PathVariable("id") Long id){
        return commentRepository.findById(id);
    }
    @GetMapping("/post/comments")
    @PreAuthorize("hasRole('USER')")
    public List<Comment> getComments(@CurrentUser UserPrincipal currentUser){
        return commentRepository.findAll();
    }
}
