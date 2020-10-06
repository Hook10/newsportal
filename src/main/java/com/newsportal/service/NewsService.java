package com.newsportal.service;

import com.newsportal.exception.ResourceNotFoundException;
import com.newsportal.entity.News;

import java.util.List;

public interface NewsService {

    public List<News> getNews();

    public void saveNews(News news);

    public News getNews(long id) throws ResourceNotFoundException;

    public void deleteNews(long id) throws ResourceNotFoundException;


}
