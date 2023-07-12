package com.msawili.blogcapstone.service;

import com.msawili.blogcapstone.exceptions.BlogNotFoundException;
import com.msawili.blogcapstone.exceptions.BloggerNotFoundException;
import com.msawili.blogcapstone.model.Blog;

import java.util.List;

public interface BlogService {
    Blog createBlog(String title, String body, String bloggerId) throws BloggerNotFoundException;
    Blog getBlog(String id) throws BlogNotFoundException;
    Blog updateBlog(String id, String title, String body) throws BlogNotFoundException;
    List<Blog> getAllBlogs();
    List<Blog> getBlogsByBlogger(String bloggerId) throws BloggerNotFoundException;
}
