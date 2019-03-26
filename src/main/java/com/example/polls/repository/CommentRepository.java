package com.example.polls.repository;

import com.example.polls.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
