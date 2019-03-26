package com.example.blog.repository;

import com.example.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
