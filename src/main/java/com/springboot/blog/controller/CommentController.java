package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST APIs for Comment Resources")
@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Create Comment REST API")
    @PostMapping("/api/v1/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId") long postId,
            @Valid @RequestBody CommentDto commentDto
    ) {

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get all Comments by POST Id REST API")
    @GetMapping("/api/v1/posts/{postId}/comments")
    public List<CommentDto> getAllComments(
            @PathVariable("postId") long postId) {

        return commentService.getCommentByPostId(postId);
    }


    @ApiOperation(value = "Get single Comment by POST Id REST API")
    @GetMapping("/api/v1/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId, @PathVariable("id") long commentId) {

        CommentDto commentDto = commentService.getCommentById(postId, commentId);

        return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
    }


    @ApiOperation(value = "Update Comment by Id REST API")
    @PutMapping("/api/v1/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId,
                                                    @PathVariable("id") long commentId,
                                                    @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);

        return new ResponseEntity<CommentDto>(updatedComment, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Comment by Id REST API")
    @DeleteMapping("/api/v1/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId") long postId,
            @PathVariable("id") long commentId){

        commentService.deleteComment(postId, commentId);
        return new ResponseEntity("Comment Deleted successfully", HttpStatus.OK);
    }
}
