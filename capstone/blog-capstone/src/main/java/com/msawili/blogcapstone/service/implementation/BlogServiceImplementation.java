package com.msawili.blogcapstone.service.implementation;

import com.msawili.blogcapstone.model.Blog;
import com.msawili.blogcapstone.model.Blogger;
import com.msawili.blogcapstone.repository.BlogRepository;
import com.msawili.blogcapstone.service.BlogService;
import com.msawili.blogcapstone.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImplementation implements BlogService {
    private final BlogRepository blogRepository;
    private final BloggerService bloggerService;

    @Autowired
    public BlogServiceImplementation(BlogRepository blogRepository, BloggerService bloggerService) {
        this.blogRepository = blogRepository;
        this.bloggerService = bloggerService;
    }

    @Override
    public Blog createBlog(String title, String body, String bloggerId) {
        Blog newBlog = new Blog();
        Blogger author = bloggerService.getBlogger(bloggerId);

        newBlog.setTitle(title);
        newBlog.setBody(body);
        newBlog.setBlogger(author);

        return blogRepository.save(newBlog);
    }

    @Override
    public Blog getBlog(String id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Blog updateBlog(String id, String title, String body) {
        Blog blog = blogRepository.findById(id).orElse(null);
        blog.setTitle(title);
        blog.setBody(body);
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        blogRepository.findAll().forEach(blogs::add);
        return blogs;
    }

    @Override
    public List<Blog> getBlogsByBlogger(String bloggerId) {
        List<Blog> blogs = new ArrayList<>();
        blogRepository.findAllByBlogger_Id(bloggerId).forEach(blogs::add);
        return blogs;
    }
}
