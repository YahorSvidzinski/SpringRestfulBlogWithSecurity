package com.example.polls.repository;

import com.example.polls.model.Post;
import com.example.polls.model.User;
import org.apache.tomcat.jni.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository<Post,Long>,CrudRepository<Post,Long>{
    List<Post> findAll();
    Post findByIdIn(Long id);
    Post findPostByUserId(Long id);
    void deletePostById(Long id);
    void deletePostsByUserId(Long id);
    void deleteByIdIn(Long id);
    Post findPostById(Long id);
}
