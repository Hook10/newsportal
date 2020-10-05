package com.newsportal.repository;

import com.newsportal.entity.NewsAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsAdminRepository extends JpaRepository<NewsAdmin, Long> {
    List<NewsAdmin> findByLogin (String login);
}
