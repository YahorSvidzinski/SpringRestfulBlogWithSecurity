package com.example.polls.controller;

import com.example.polls.exception.ResourceNotFoundException;
import com.example.polls.model.User;
import com.example.polls.payload.UserSummary;
import com.example.polls.repository.UserRepository;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.UserPrincipal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }
    @GetMapping("/user/fullinfo/{id}")
    public User getCurrentUser(@PathVariable("id") Long id) {
        return userRepository.findUserById(id).
                orElseThrow(()->new ResourceNotFoundException("user id","not found",id));
    }

}
