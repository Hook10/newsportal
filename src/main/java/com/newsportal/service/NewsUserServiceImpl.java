package com.newsportal.service;

import com.newsportal.entity.NewsUser;
import com.newsportal.exception.ResourceNotFoundException;
import com.newsportal.repository.NewsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsUserServiceImpl implements NewsUserService {

    @Autowired
    private NewsUserRepository newsUserRepository;

    @Override
    @Transactional
    public List<NewsUser> getNewsUser() {
        return newsUserRepository.findAll();
    }

    @Override
    @Transactional
    public void saveNewsUser(NewsUser newsUser) {
        newsUserRepository.save(newsUser);
    }

    @Override
    @Transactional
    public NewsUser getNewsUser(long id) throws ResourceNotFoundException {
        return newsUserRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteNewsUser(long id) throws ResourceNotFoundException {
        newsUserRepository.deleteById(id);
    }
}
