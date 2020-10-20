package com.newsportal.controllers;


import com.newsportal.entity.NewsUser;
import com.newsportal.exception.ResourceNotFoundException;
import com.newsportal.repository.NewsUserRepository;
import com.newsportal.service.UserService;
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
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class NewsUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsUserController.class);


    //just test user with service

    @Autowired
    private UserService service;


    @PostMapping("/registeruser")
    @CrossOrigin(origins = "http://localhost:4200")
    public NewsUser registerUser(@RequestBody NewsUser newsUser) throws Exception {
        String tempEmail = newsUser.getEmail();
        if (tempEmail != null && !"".equals(tempEmail)) {
            Optional<NewsUser> newsUser1 = service.fetchUserByEmail(tempEmail);
            if (newsUser1.isPresent()) {
                throw new Exception("User with this email: " + tempEmail + " is already exist");
            }
        }
        return service.saveUser(newsUser);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public Optional<NewsUser> loginUser(@RequestBody NewsUser user) throws Exception {
        String tempEmail = user.getEmail();
        String tempPassword = user.getPassword();
        Optional<NewsUser> newsUser = null;
        if (tempEmail != null && tempPassword != null) {
            newsUser = service.fetchUserByEmailAndPassword(tempEmail, tempPassword);
        }
        if(newsUser == null){
            throw new Exception("bad credentials");
        }
        return newsUser;
    }

    // Everything below this comment has been created before creating registration and login  methods


    @Autowired
    private NewsUserRepository newsUserRepository;

    // get all users

    @GetMapping("/users")
    public List<NewsUser> getAllNewsUsers() {
        return newsUserRepository.findAll();
    }

    //save user

    @PostMapping("/users")
    public NewsUser saveNewsUser(@RequestBody NewsUser newsUser) {
        return newsUserRepository.save(newsUser);
    }

    // get one  by id

    @GetMapping("/users/{id}")
    public ResponseEntity<NewsUser> getNewsUserById(@PathVariable Long id) throws ResourceNotFoundException {
        NewsUser user = newsUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with this id:  " + id));
        return ResponseEntity.ok(user);
    }

    // update user

    @PutMapping("/users/{id}")
    public ResponseEntity<NewsUser> updateNewsUser(@PathVariable Long id, @RequestBody NewsUser newsUserToUpdate) throws ResourceNotFoundException {
        NewsUser user = newsUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with this id:  " + id));

        user.setEmail(newsUserToUpdate.getEmail());
        user.setPassword(newsUserToUpdate.getPassword());


        NewsUser updatedNewsUser = newsUserRepository.save(user);
        return ResponseEntity.ok(updatedNewsUser);
    }

    //delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteNewsUser(@PathVariable Long id) throws ResourceNotFoundException {
        NewsUser user = newsUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not exist with this id:  " + id));
        newsUserRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }


//    @GetMapping("user-list")
//    public String listUsers(Model theModel) {
//        List<NewsUser> theNewsUser = newsUserService.getNewsUser();
//        theModel.addAttribute("user", theNewsUser);
//        return "all-users";
//    }
//
//    @GetMapping("showUserForm")
//    public String showUserFormForAdd(Model model) {
//        LOGGER.debug("inside show user form handler method");
//        NewsUser newsUser = new NewsUser();
//        model.addAttribute("user", newsUser);
//        return "user-form";
//    }
//


//    @GetMapping("updateUserForm")
//    public String showUserFormForUpdate(@RequestParam("userId") long theId,
//                                        Model model) throws ResourceNotFoundException {
//        NewsUser newsUser = newsUserService.getNewsUser(theId);
//        model.addAttribute("user", newsUser);
//        return "user-form";
//    }
//
//    @GetMapping("delete")
//    public String deleteNewsUser(@RequestParam("userId") long id) throws ResourceNotFoundException {
//        newsUserService.deleteNewsUser(id);
//        return "redirect:/news-user/user-list";
//    }


}
