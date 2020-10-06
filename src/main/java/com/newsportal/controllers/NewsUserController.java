package com.newsportal.controllers;

import com.newsportal.entity.NewsUser;
import com.newsportal.exception.ResourceNotFoundException;
import com.newsportal.service.NewsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/news-user")
public class NewsUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsUserController.class);

    @Autowired
    private NewsUserService newsUserService;

    @GetMapping("/user-list")
    public String listUsers(Model theModel) {
        List<NewsUser> theNewsUser = newsUserService.getNewsUser();
        theModel.addAttribute("user", theNewsUser);
        return "/all-users";
    }

    @GetMapping("/showUserForm")
    public String showUserFormForAdd(Model model) {
        LOGGER.debug("inside show user form handler method");
        NewsUser newsUser = new NewsUser();
        model.addAttribute("user", newsUser);
        return "/user-form";
    }

    @PostMapping("/saveNewsUser")
    public String saveNewsUser(@ModelAttribute("user") NewsUser newsUser) {
        newsUserService.saveNewsUser(newsUser);
        return "redirect:/news-user/user-list";
    }

    @GetMapping("/updateUserForm")
    public String showUserFormForUpdate(@RequestParam("userId") long theId,
                                        Model model) throws ResourceNotFoundException {
        NewsUser newsUser = newsUserService.getNewsUser(theId);
        model.addAttribute("user", newsUser);
        return "/user-form";
    }

    @GetMapping("/delete")
    public String deleteNewsUser(@RequestParam("userId") long id) throws ResourceNotFoundException {
        newsUserService.deleteNewsUser(id);
        return "redirect:/news-user/user-list";
    }


}
