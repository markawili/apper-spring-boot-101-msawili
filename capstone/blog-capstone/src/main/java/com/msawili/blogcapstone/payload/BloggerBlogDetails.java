package com.msawili.blogcapstone.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BloggerBlogDetails {
    @JsonProperty("blogger_id")
    @JsonIgnore
    private String bloggerId;

    private String title;

    private String body;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
}
