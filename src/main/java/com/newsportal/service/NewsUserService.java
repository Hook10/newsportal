package com.newsportal.service;

import com.newsportal.entity.NewsUser;
import com.newsportal.exception.ResourceNotFoundException;

import java.util.List;

public interface NewsUserService {

    public List<NewsUser> getNewsUser();

    public void saveNewsUser(NewsUser newsUser);

    public NewsUser getNewsUser(long id) throws ResourceNotFoundException;

    public void deleteNewsUser(long id) throws ResourceNotFoundException;
}
