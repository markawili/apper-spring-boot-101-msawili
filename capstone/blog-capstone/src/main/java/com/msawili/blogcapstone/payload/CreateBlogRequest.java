package com.msawili.blogcapstone.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBlogRequest {
    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Body must not be blank")
    private String body;

    @NotBlank(message = "Blogger ID must not be blank")
    @JsonProperty("blogger_id")
    private String bloggerId;
}
