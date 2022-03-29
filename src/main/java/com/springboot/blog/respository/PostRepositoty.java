package com.springboot.blog.respository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepositoty extends JpaRepository<Post, Long> {
}
