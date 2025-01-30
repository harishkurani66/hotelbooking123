package com.microservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post {

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Id
    @Column(name = "post_id", nullable = false)
    private String postId;



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

}