package com.msawili.blogcapstone.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateBlogRequest {
    @NotBlank(message = "Title must not be empty")
    private String title;

    @NotBlank(message = "Body must not be empty")
    private String body;
}
