package com.msawili.blogcapstone.service;

import com.msawili.blogcapstone.exceptions.BloggerNotFoundException;
import com.msawili.blogcapstone.exceptions.EmailAlreadyRegisteredException;
import com.msawili.blogcapstone.model.Blogger;

import java.util.List;

public interface BloggerService {
    public Blogger createBlogger(String email, String name, String password) throws EmailAlreadyRegisteredException;
    public Blogger getBlogger(String id) throws BloggerNotFoundException;
    public List<Blogger> getAllBloggers();

}
