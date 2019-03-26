package com.example.polls.model;

import com.example.polls.model.audit.DateAudit;
import com.example.polls.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    @Size(max = 500)
    private String body;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
