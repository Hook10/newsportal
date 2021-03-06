package com.newsportal.repository;

import com.newsportal.entity.NewsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsUserRepository extends JpaRepository<NewsUser, Long> {
    Optional<NewsUser> findById(Long id);

    public NewsUser findByEmail(String email);

    public NewsUser findByEmailAndPassword(String email, String password);

    public String findPasswordByEmail(String email);
}
