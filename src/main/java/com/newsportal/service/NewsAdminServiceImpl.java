package com.newsportal.service;

import com.newsportal.entity.NewsAdmin;
import com.newsportal.exception.ResourceNotFoundException;
import com.newsportal.repository.NewsAdminRepository;
import com.newsportal.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsAdminServiceImpl implements NewsAdminService {

    @Autowired
    private NewsAdminRepository newsAdminRepository;

    @Override
    @Transactional
    public List<NewsAdmin> getNewsAdmin() {
        return newsAdminRepository.findAll();
    }

    @Override
    @Transactional
    public void saveAdmin(NewsAdmin newsAdmin) {
        newsAdminRepository.save(newsAdmin);
    }

    @Override
    @Transactional
    public NewsAdmin getNewsAdmin(long id) throws ResourceNotFoundException {
        return newsAdminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public void deleteNewsAdmin(long id) throws ResourceNotFoundException {
        newsAdminRepository.deleteById(id);
    }
}
