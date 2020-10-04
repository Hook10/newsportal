package com.newsportal.controllers;

import com.newsportal.entity.News;
import com.newsportal.exception.ResourceNotFoundException;
import com.newsportal.repository.NewsRepository;
import com.newsportal.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    private static final Logger LOG = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        List<News> theNews = newsService.getNews();
        theModel.addAttribute("news", theNews);
        return "list-news";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model model) {
        LOG.debug("inside show news form handler method");
        News news = new News();
        model.addAttribute("news", news);
        return "news-form";
    }

    @PostMapping("/saveNews")
    public String saveNews(@ModelAttribute("news") News news) {
        newsService.saveNews(news);
        return "redirect:/news/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("newsId") long theId,
                                    Model model) throws ResourceNotFoundException {
        News news = newsService.getNews(theId);
        model.addAttribute("news", news);
        return "news-form";
    }

    @GetMapping("/delete")
    public String deleteNews(@RequestParam("newsId") long id) throws ResourceNotFoundException{
        newsService.deleteNews(id);
        return "redirect:/news/list";
    }


//    @GetMapping("/showForm")
//    public String showNewsForm() {
//        return "/add-news";
//
//    }
//
//    @GetMapping("/list")
//    public String news(Model model){
//        model.addAttribute("news", this.newsService.getNews());
//        return "index";
//    }
//
//    @PostMapping("/add")
//    public String addNews(News news, BindingResult result, Model Model){
//        if(result.hasErrors()){
//            return "add-news";
//        }
//        this.newsRepository.save(news);
//        return "redirect:list";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        News news = this.newsRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid news id : " + id));
//
//        model.addAttribute("news", news);
//        return  "update-news";
//    }
//
//    @GetMapping("/update/{id}")
//    public String updateNews(@PathVariable("id") long id, @Valid News news, BindingResult result, Model model) {
//        if(result.hasErrors()){
//            news.setId(id);
//            return "update-news";
//        }
//        newsRepository.save(news);
//        model.addAttribute("news", this.newsRepository.findAll());
//        return "index";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteNews(@PathVariable("id") long id, Model model) {
//        News news = this.newsRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid news id : " + id));
//
//        this.newsRepository.delete(news);
//        model.addAttribute("news", this.newsRepository.findAll());
//        return "index";
//    }

//
//    @GetMapping("/hello")
//    public String helloPage(){
//        return "first/hello";
//    }


}
