package com.microservice.controller;

import com.microservice.entity.Post;
import com.microservice.payload.PostDto;
import com.microservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        Post posts=postService.createPost(post);
        return new ResponseEntity<>(posts, HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<Post>getPostById(@PathVariable String postId) {
        Post post = postService.findByPostId(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @GetMapping("/{postId}/comments")
    public ResponseEntity<PostDto>getPostWithComments(@PathVariable String postId){
        PostDto postDto = postService.getPostWithComments(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
}
