package com.msawili.blogcapstone.service.implementation;

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

    @Autowired
    public BloggerServiceImplementation(BloggerRepository bloggerRepository) {
        this.bloggerRepository = bloggerRepository;
    }

    @Override
    public Blogger createBlogger(String email, String name, String password) {
        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);

        return bloggerRepository.save(blogger);
    }

    @Override
    public Blogger getBlogger(String id) {
        return bloggerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Blogger> getAllBloggers() {
        List<Blogger> bloggers = new ArrayList<>();
        bloggerRepository.findAll().forEach(bloggers::add);
        return bloggers;
    }
}
