package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {

    public PostDto createPost(PostDto postDto);

    public PostResponse getAllPost(int pageNo, int pageSize, String soryBy, String sortDir);

    public PostDto getPostById(long id);

    public PostDto updatePost(PostDto postDto, long id);

    public void deletePost(long id);
}
