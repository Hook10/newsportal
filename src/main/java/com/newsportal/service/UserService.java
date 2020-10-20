package com.newsportal.service;

import com.newsportal.entity.NewsUser;
import com.newsportal.repository.NewsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private NewsUserRepository newsUserRepository;

    public NewsUser saveUser(NewsUser newsUser) {
        return newsUserRepository.save(newsUser);
    }

    public Optional<NewsUser> fetchUserByEmail(String email){
        return newsUserRepository.findByEmail(email);
    }

    public Optional<NewsUser> fetchUserByEmailAndPassword(String email, String password){
        return newsUserRepository.findByEmailAndPassword(email, password);
    }


}
