package com.msawili.blogcapstone.controller;

import com.msawili.blogcapstone.exceptions.BlogNotFoundException;
import com.msawili.blogcapstone.exceptions.BloggerNotFoundException;
import com.msawili.blogcapstone.model.Blog;
import com.msawili.blogcapstone.payload.*;
import com.msawili.blogcapstone.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogApi {
    private final BlogService blogService;
    private final CustomExceptionHandler customExceptionHandler;
    @Autowired
    public BlogApi(BlogService blogService, CustomExceptionHandler customExceptionHandler) {
        this.blogService = blogService;
        this.customExceptionHandler = customExceptionHandler;
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request) throws BloggerNotFoundException {
        Blog blog = blogService.createBlog(request.getTitle(), request.getBody(), request.getBloggerId());

        return getSaveBlogResponse(blog);
    }

    @GetMapping("get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDetails getBlog(@PathVariable String id) throws BlogNotFoundException {
        Blog blog = blogService.getBlog(id);

        return getBlogDetails(blog);

    }

    @GetMapping("get")
    @ResponseStatus(HttpStatus.OK)
    public List<BlogDetails> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();

        return getBlogDetails(blogs);
    }

    @GetMapping("blogger/{blogger_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<BloggerBlogDetails> getBlogsByBlogger(@PathVariable String blogger_id) throws BloggerNotFoundException {
        List<Blog> blogs = blogService.getBlogsByBlogger(blogger_id);

        return getBloggerBlogDetails(blogs);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BlogResponse updateBlog(@PathVariable String id, @RequestBody @Valid UpdateBlogRequest request) throws BlogNotFoundException {
        Blog blog = blogService.updateBlog(id, request.getTitle(), request.getBody());

        return getSaveBlogResponse(blog);

    }

    private static BlogResponse getSaveBlogResponse(Blog blog) {
        BlogResponse response = new BlogResponse();
        response.setBloggerId(blog.getBlogger().getId());
        response.setId(blog.getId());
        response.setCreatedAt(blog.getCreatedAt());
        response.setLastUpdated(blog.getLastUpdate());
        return response;
    }

    private static List<BlogDetails> getBlogDetails(List<Blog> blogs) {
        List<BlogDetails> response = new ArrayList<>();
        for (Blog blog : blogs) {
            BlogDetails blogDetails = getBlogDetails(blog);

            response.add(blogDetails);
        }
        return response;
    }

    private static BlogDetails getBlogDetails(Blog blog) {
        BlogDetails blogDetails = new BlogDetails();
        blogDetails.setBloggerId(blog.getBlogger().getId());
        blogDetails.setTitle(blog.getTitle());
        blogDetails.setBody(blog.getBody());
        blogDetails.setCreatedAt(blog.getCreatedAt());
        blogDetails.setLastUpdated(blog.getLastUpdate());
        return blogDetails;
    }

    private static List<BloggerBlogDetails> getBloggerBlogDetails(List<Blog> blogs) {
        List<BloggerBlogDetails> response = new ArrayList<>();
        for (Blog blog : blogs) {
            BloggerBlogDetails blogDetails = getBloggerBlogDetails(blog);

            response.add(blogDetails);
        }
        return response;
    }

    private static BloggerBlogDetails getBloggerBlogDetails(Blog blog) {
        BloggerBlogDetails blogDetails = new BloggerBlogDetails();
        blogDetails.setBloggerId(blog.getBlogger().getId());
        blogDetails.setTitle(blog.getTitle());
        blogDetails.setBody(blog.getBody());
        blogDetails.setCreatedAt(blog.getCreatedAt());
        blogDetails.setLastUpdated(blog.getLastUpdate());
        return blogDetails;
    }

}
