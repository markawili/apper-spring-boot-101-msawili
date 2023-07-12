package com.msawili.blogcapstone.service;

import com.msawili.blogcapstone.model.Blog;

import java.util.List;

public interface BlogService {
    Blog createBlog(String title, String body, String bloggerId);
    Blog getBlog(String id);
    Blog updateBlog(String id, String title, String body);
    List<Blog> getAllBlogs();
    List<Blog> getBlogsByBlogger(String bloggerId);
}
