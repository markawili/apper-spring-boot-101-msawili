package com.msawili.blogcapstone.controller;

import com.msawili.blogcapstone.exceptions.BloggerNotFoundException;
import com.msawili.blogcapstone.exceptions.EmailAlreadyRegisteredException;
import com.msawili.blogcapstone.model.Blogger;
import com.msawili.blogcapstone.payload.BloggerDetailsResponse;
import com.msawili.blogcapstone.payload.CreateBloggerRequest;
import com.msawili.blogcapstone.payload.CreateBloggerResponse;
import com.msawili.blogcapstone.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blogger")
public class BloggerApi {
    private final BloggerService bloggerService;

    @Autowired
    public BloggerApi(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request) throws EmailAlreadyRegisteredException {
        Blogger newBlogger = bloggerService.createBlogger(request.getEmail(), request.getName(), request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(newBlogger.getId());
        response.setDateRegistration(newBlogger.getCreatedAt());

        return response;
    }

    @GetMapping("get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BloggerDetailsResponse getBlogger(@PathVariable String id) throws BloggerNotFoundException {
        Blogger blogger = bloggerService.getBlogger(id);

        return getBloggerDetailsResponse(blogger);
    }

    @GetMapping("get")
    @ResponseStatus(HttpStatus.OK)
    public List<BloggerDetailsResponse> getAllBloggers() {
        List<Blogger> bloggerList = bloggerService.getAllBloggers();
        List<BloggerDetailsResponse> response = new ArrayList<BloggerDetailsResponse>();

        for (Blogger blogger : bloggerList) {
            BloggerDetailsResponse itemResponse = getBloggerDetailsResponse(blogger);
            response.add(itemResponse);
        }

        return response;
    }

    private static BloggerDetailsResponse getBloggerDetailsResponse(Blogger blogger) {
        BloggerDetailsResponse itemResponse = new BloggerDetailsResponse();
        itemResponse.setId(blogger.getId());
        itemResponse.setEmail(blogger.getEmail());
        itemResponse.setName(blogger.getName());
        itemResponse.setDateRegistration(blogger.getCreatedAt());
        return itemResponse;
    }

}
