package com.example.blog.payload;

import com.example.blog.model.Post;
import com.example.blog.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class PostRequest extends Post{
    @NotBlank
    @Size(max = 140)
    private String title;

    @NotNull
    @Size(max = 500)
    private String body;

    private User user;
}
