package com.newsportal.controllers;

import com.newsportal.entity.News;
import com.newsportal.exception.ResourceNotFoundException;
import com.newsportal.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class NewsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/news")
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    // create news rest api
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/news")
    public News createNews(@RequestBody News news) {
        return newsRepository.save(news);
    }


    // get news by id rest api
    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) throws ResourceNotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not exist with this id:  " + id));
        return ResponseEntity.ok(news);
    }

    //  update news rest api

    @PutMapping("/news/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id,@RequestBody News newsToUpdate) throws ResourceNotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not exist with this id:  " + id));

        news.setCategory(newsToUpdate.getCategory());
        news.setName(newsToUpdate.getName());
        news.setContent(newsToUpdate.getContent());

        News updatedNews = newsRepository.save(news);
        return ResponseEntity.ok(updatedNews);
    }

    //delete news rest api

    @DeleteMapping("/news/{id}")
    public ResponseEntity< Map<String, Boolean>> deleteNews(@PathVariable Long id ) throws ResourceNotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not exist with this id:  " + id));
        newsRepository.delete(news);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

//
//    @GetMapping("/news")
//    public String listNews(Model theModel) {
//        List<News> theNews = newsService.getNews();
//        theModel.addAttribute("news", theNews);
//        return "list-news";
//    }
//
//    @GetMapping("showForm")
//    public String showFormForAdd(Model model) {
//        LOGGER.debug("inside show news form handler method");
//        News news = new News();
//        model.addAttribute("news", news);
//        return "news-form";
//    }
//
//    @PostMapping("saveNews")
//    public String saveNews(@ModelAttribute("news") News news) {
//        newsService.saveNews(news);
//        return "redirect:/news/list";
//    }
//
//    @GetMapping("updateForm")
//    public String showFormForUpdate(@RequestParam("newsId") long theId,
//                                    Model model) throws ResourceNotFoundException {
//        News news = newsService.getNews(theId);
//        model.addAttribute("news", news);
//        return "news-form";
//    }
//
//    @GetMapping("delete")
//    public String deleteNews(@RequestParam("newsId") long id) throws ResourceNotFoundException{
//        newsService.deleteNews(id);
//        return "redirect:/news/list";
//    }

}
