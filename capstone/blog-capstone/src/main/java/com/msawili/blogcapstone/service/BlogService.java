package com.msawili.blogcapstone.service;

import com.msawili.blogcapstone.model.Blog;

import java.util.List;

public interface BlogService {
    public Blog createBlog(String title, String body, String bloggerId);
    public Blog getBlog(String id);
    public Blog updateBlog(String id, String title, String body);
    public List<Blog> getAllBlogs();
    public List<Blog> getBlogsByBlogger(String bloggerId);
}
