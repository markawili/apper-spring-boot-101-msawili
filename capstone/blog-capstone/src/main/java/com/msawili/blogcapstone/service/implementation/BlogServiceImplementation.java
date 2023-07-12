package com.msawili.blogcapstone.service.implementation;

import com.msawili.blogcapstone.exceptions.BlogNotFoundException;
import com.msawili.blogcapstone.exceptions.BloggerNotFoundException;
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
    public Blog createBlog(String title, String body, String bloggerId) throws BloggerNotFoundException {
        Blog newBlog = new Blog();
        Blogger author = getBloggerById(bloggerId);

        newBlog.setTitle(title);
        newBlog.setBody(body);
        newBlog.setBlogger(author);

        return blogRepository.save(newBlog);
    }

    @Override
    public Blog getBlog(String id) throws BlogNotFoundException {
        Blog blog = blogRepository.findById(id).orElse(null);

        if (blog == null) {
            throw new BlogNotFoundException("Blog not found");
        } else {
            return blog;
        }
    }

    @Override
    public Blog updateBlog(String id, String title, String body) throws BlogNotFoundException {
        Blog blog = getBlog(id);
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
    public List<Blog> getBlogsByBlogger(String bloggerId) throws BloggerNotFoundException {
        if (bloggerService.getBlogger(bloggerId) == null) {
            throw new BloggerNotFoundException("Blogger not found");
        }
        return new ArrayList<>(blogRepository.findAllByBlogger_Id(bloggerId));
    }

    public  Blogger getBloggerById(String id) throws BloggerNotFoundException {
        Blogger blogger = bloggerService.getBlogger(id);
        if (blogger == null) {
            throw new BloggerNotFoundException("Blogger not found");
        } else {
            return blogger;
        }
    }

}
