package com.newsportal.controllers;

import com.newsportal.entity.NewsUser;
import com.newsportal.exception.EmailNotFoundException;
import com.newsportal.exception.LoginException;
import com.newsportal.exception.ResourceNotFoundException;
import com.newsportal.repository.NewsUserRepository;
import com.newsportal.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class NewsUserController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    //just test user with service
    @Autowired
    private UserService service;


    @PostMapping("/registeruser")
    public NewsUser registerUser(@RequestBody NewsUser newsUser) throws Exception {
        String tempEmail = newsUser.getEmail();
        if (tempEmail != null && StringUtils.isNotEmpty(tempEmail)) {
            NewsUser newsUser1 = service.fetchUserByEmail(tempEmail);
            if (newsUser1 != null) {
                throw new Exception("User with this email: " + tempEmail + " is already exist");
            }
            String encryptedPassword = passwordEncoder.encode(newsUser.getPassword());
            newsUser.setPassword(encryptedPassword);

        }
        return service.saveUser(newsUser);
    }


//this method works well do not remove it
//    @PostMapping("/login")
//    public NewsUser loginUser(@RequestBody NewsUser user) throws LoginException {
//        String tempEmail = user.getEmail();
//        String tempPassword = user.getPassword();
//        NewsUser newsUser =  newsUserRepository.findByEmail(tempEmail);
//        boolean isMatches = passwordEncoder.matches(tempPassword, newsUser.getPassword());
//
//               if(isMatches){
//                   return newsUser;
//               } else throw new LoginException("Login exception. Bad credentials");
//    }

    @PostMapping("/login")
    public NewsUser loginUser(@RequestBody NewsUser user) throws LoginException, EmailNotFoundException {
        String tempEmail = user.getEmail();
        String tempPassword = user.getPassword();

        NewsUser userFromDB = Optional.ofNullable(service.fetchUserByEmail(tempEmail))
                .orElseThrow(() -> new EmailNotFoundException("email doesn't exist"));
        return userPasswordValidation(tempPassword, userFromDB);
    }


    public NewsUser userPasswordValidation(String tempPassword, NewsUser userFromDB) throws LoginException {
        boolean isMatches = passwordEncoder.matches(tempPassword, userFromDB.getPassword());

        if (isMatches) {
            return userFromDB;
        } else throw new LoginException("Login exception. Bad credentials");
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
