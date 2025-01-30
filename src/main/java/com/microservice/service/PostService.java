package com.microservice.service;

import com.microservice.config.RestTemplateConfig;
import com.microservice.entity.Post;
import com.microservice.payload.Comment;
import com.microservice.payload.PostDto;
import com.microservice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    private PostRepository postRepository;
    private RestTemplateConfig restTemplate;

    public PostService(PostRepository postRepository, RestTemplateConfig restTemplate) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
    }
    public Post createPost(Post post){
        String postId = UUID.randomUUID().toString();
        post.setPostId(postId);
        return postRepository.save(post);
    }

    public Post findByPostId(String postId) {
        Post posts = postRepository.findById(postId).get();
      return posts;
    }

    public PostDto getPostWithComments(String postId) {
        Post post = postRepository.findById(postId).get();
       ArrayList comments = restTemplate.getRestTemplate().getForObject("http://localhost:8082/api/v1/comments/"+postId, ArrayList.class);
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setComments(comments);
        return postDto;
    }
}
