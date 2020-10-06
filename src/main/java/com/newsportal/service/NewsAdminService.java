package com.newsportal.service;

import com.newsportal.entity.News;
import com.newsportal.entity.NewsAdmin;
import com.newsportal.exception.ResourceNotFoundException;

import java.util.List;

public interface NewsAdminService {

    public List<NewsAdmin> getNewsAdmin();

    public void saveAdmin(NewsAdmin newsAdmin);

    public NewsAdmin getNewsAdmin(long id) throws ResourceNotFoundException;

    public void deleteNewsAdmin(long id) throws ResourceNotFoundException;
}
