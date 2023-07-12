package com.msawili.blogcapstone.service.implementation;

import com.msawili.blogcapstone.controller.CustomExceptionHandler;
import com.msawili.blogcapstone.exceptions.BloggerNotFoundException;
import com.msawili.blogcapstone.exceptions.EmailAlreadyRegisteredException;
import com.msawili.blogcapstone.model.Blogger;
import com.msawili.blogcapstone.repository.BloggerRepository;
import com.msawili.blogcapstone.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BloggerServiceImplementation implements BloggerService {
    private final BloggerRepository bloggerRepository;
    private final CustomExceptionHandler customExceptionHandler;

    @Autowired
    public BloggerServiceImplementation(BloggerRepository bloggerRepository, CustomExceptionHandler customExceptionHandler) {
        this.bloggerRepository = bloggerRepository;
        this.customExceptionHandler = customExceptionHandler;
    }

    @Override
    public Blogger createBlogger(String email, String name, String password) throws EmailAlreadyRegisteredException {
        if (bloggerRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException("Email already registered");
        }

        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);

        return bloggerRepository.save(blogger);
    }

    @Override
    public Blogger getBlogger(String id) throws BloggerNotFoundException {
        Blogger blogger = bloggerRepository.findById(id).orElse(null);
        if (blogger == null) {
            throw new BloggerNotFoundException("Blogger not found");
        }
        return blogger;
    }

    @Override
    public List<Blogger> getAllBloggers() {
        List<Blogger> bloggers = new ArrayList<>();
        bloggerRepository.findAll().forEach(bloggers::add);
        return bloggers;
    }
}
