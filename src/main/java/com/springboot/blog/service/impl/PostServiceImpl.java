package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundEception;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.respository.PostRepositoty;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    private PostRepositoty postRepositoty;

    private ModelMapper modelMapper;


    public PostServiceImpl(PostRepositoty postRepositoty, ModelMapper modelMapper) {

        this.postRepositoty = postRepositoty;
        this.modelMapper = modelMapper;
    }

    @Override
        public PostDto createPost(PostDto postDto) {

        //Covert PostDTO to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepositoty.save(post);

        //Covert entity to PostDTO
        PostDto newDto = mapToDTO(newPost);

            return newDto;
        }

        public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir){

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            //Create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepositoty.findAll(pageable);

        //get content for page object
            List<Post> listOfPost = posts.getContent();

            List<PostDto> content = listOfPost.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
            PostResponse postResponse = new PostResponse();

            postResponse.setContent(content);
            postResponse.setPageNo(posts.getNumber());
            postResponse.setPageSize(posts.getSize());
            postResponse.setTotalElement(posts.getTotalElements());
            postResponse.setTotalPages(posts.getTotalPages());
            postResponse.setLast(posts.isLast());

        return postResponse;
        }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepositoty.findById(id).orElseThrow(()-> new ResourceNotFoundEception("Post", "Id", id));

        return mapToDTO(post);

    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {


        Post post = postRepositoty.findById(id).orElseThrow(()-> new ResourceNotFoundEception("Post", "Id", id));
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post newPost = postRepositoty.save(post);

        return mapToDTO(newPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepositoty.findById(id).orElseThrow( () -> new ResourceNotFoundEception("Post", "Id", id));
        postRepositoty.deleteById(id);
    }

    public PostDto mapToDTO(Post post){

        PostDto postDto = modelMapper.map(post, PostDto.class);


           /* PostDto newDto = new PostDto();
            newDto.setId(post.getId());
            newDto.setTitle(post.getDescription());
            newDto.setDescription(post.getDescription());
            newDto.setContent(post.getContent());*/
        return postDto;
    }

    public Post mapToEntity(PostDto postDto){

        Post post = modelMapper.map(postDto, Post.class);

        /*Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());*/
        return post;
    }






}
