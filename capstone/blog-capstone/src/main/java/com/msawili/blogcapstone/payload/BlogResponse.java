package com.msawili.blogcapstone.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogResponse {
    private String id;

    @JsonProperty("blogger_id")
    private String bloggerId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;

}
