package com.msawili.blogcapstone.service;

import com.msawili.blogcapstone.model.Blogger;

import java.util.List;

public interface BloggerService {
    public Blogger createBlogger(String email, String name, String password);
    public Blogger getBlogger(String id);
    public List<Blogger> getAllBloggers();

}
