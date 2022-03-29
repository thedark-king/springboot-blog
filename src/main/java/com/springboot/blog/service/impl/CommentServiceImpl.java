package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundEception;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.respository.CommentRepository;
import com.springboot.blog.respository.PostRepositoty;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepositoty postRepositoty;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepositoty postRepositoty, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepositoty = postRepositoty;
        this.modelMapper = modelMapper;
    }


    public CommentDto createComment(long id, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        //retrive Post entity by id
        Post post = postRepositoty.findById(id).orElseThrow(() -> new ResourceNotFoundEception("Post", "Id", id));

        //Set Post to comment entity
        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    public List<CommentDto> getCommentByPostId(long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepositoty.findById(postId).orElseThrow(() ->
                new ResourceNotFoundEception("Post", "Id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundEception("Comment", "Id", commentId));


        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Does not belog to this Post");
        }


        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {

        Post post = postRepositoty.findById(postId).orElseThrow(() ->
                new ResourceNotFoundEception("Post", "Id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundEception("Comment", "Id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Does not belog to this Post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);


        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepositoty.findById(postId).orElseThrow(() ->
                new ResourceNotFoundEception("Post", "Id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundEception("Comment", "Id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Does not belog to this Post");
        }

        commentRepository.deleteById(commentId);



    }


    private CommentDto mapToDto(Comment comment) {


        //Mapping Entity to DTO
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);

       /* CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());*/

        return commentDto;
    }


    private Comment mapToEntity(CommentDto commentDto) {

        //Mapping DTO to entity
        Comment comment = modelMapper.map(commentDto, Comment.class);

        /*Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());*/

        return comment;
    }
}
