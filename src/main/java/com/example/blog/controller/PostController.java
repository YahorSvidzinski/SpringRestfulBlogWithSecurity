package com.example.blog.controller;

import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.security.CurrentUser;
import com.example.blog.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @PostMapping("/create/")
    @PreAuthorize("hasRole('USER')")
    public Post createPost(@CurrentUser UserPrincipal currentUser,
                           @Valid @RequestBody Post post) {
        return userRepository.findById(currentUser.getId()).map(user -> {
            post.setUser(user);
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("Post id", "not found", "id"));
    }

    @GetMapping("/get/")
    @PreAuthorize("hasRole('USER')")
    public List<Post> postList(@CurrentUser UserPrincipal currentUser) {
        return postRepository.findAll();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('USER')")
    public Post postById(@CurrentUser UserPrincipal currentUser,@PathVariable("id")Long id) {
        return postRepository.findPostById(id);
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deletePost(@CurrentUser UserPrincipal currentUser, @PathVariable("id") Long id) {
        postRepository.deletePostById(id);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER')")
    public Post editPost(@CurrentUser UserPrincipal currentUser,@Valid @RequestBody Post post,@PathVariable("id") Long id){
        Post posted = postRepository.findPostById(id);
        posted.setTitle(post.getTitle());
        posted.setBody(post.getBody());
        return postRepository.save(posted);
    }
}