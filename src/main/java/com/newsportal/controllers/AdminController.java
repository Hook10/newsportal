package com.newsportal.controllers;

import com.newsportal.entity.NewsAdmin;
import com.newsportal.exception.ResourceNotFoundException;
import com.newsportal.service.NewsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/news-admin")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private NewsAdminService newsAdminService;

    @GetMapping("/admin-list")
    public String adminList(Model theModel) {
        List<NewsAdmin> adminList = newsAdminService.getNewsAdmin();
        theModel.addAttribute("admin", adminList);
        return "/all-admins";
    }

    @GetMapping("/showAdminForm")
    public String showFormForAdminAdd(Model model) {
        LOGGER.debug("inside show admin form handler method");
        NewsAdmin newsAdmin = new NewsAdmin();
        model.addAttribute("newsAdmin", newsAdmin);
        return "/admin-form";
    }

    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute("admin") NewsAdmin admin) {
        newsAdminService.saveAdmin(admin);
        return "redirect:/news-admin/all-admins";
    }

    @GetMapping("/updateAdminForm")
    public String showAdminFormForUpdate(@RequestParam("adminId") long theId,
                                         Model model) throws ResourceNotFoundException {
        NewsAdmin newsAdmin = newsAdminService.getNewsAdmin(theId);
        model.addAttribute("admin", newsAdmin);
        return "/showAdminForm";
    }

    @GetMapping("delete")
    public String deleteNewsAdmin(@RequestParam("adminId") long id) throws ResourceNotFoundException {
        newsAdminService.deleteNewsAdmin(id);
        return "redirect:/news-admin/add-admins";
    }

}
