package com.newsportal.service;

import com.newsportal.exception.ResourceNotFoundException;
import com.newsportal.entity.News;
import com.newsportal.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    @Transactional
    public List<News> getNews() {
        return newsRepository.findAll();
    }

    @Override
    @Transactional
    public void saveNews(News news) {
        newsRepository.save(news);
    }

    @Override
    @Transactional
    public News getNews(long id) throws ResourceNotFoundException {
        return newsRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteNews(long id)  {
        newsRepository.deleteById(id);
    }


    }

