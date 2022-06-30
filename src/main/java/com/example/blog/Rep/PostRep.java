package com.example.blog.Rep;

import com.example.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRep extends CrudRepository<Post, Long> {
}
