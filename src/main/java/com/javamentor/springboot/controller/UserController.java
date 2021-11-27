package com.javamentor.springboot.controller;

import com.javamentor.springboot.model.User;
import com.javamentor.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "getUser")
    public ResponseEntity<List<User>> getUsers(@AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUserName(currentUser.getUsername());
        return new ResponseEntity<List<User>>(Collections.singletonList(user), HttpStatus.OK);
    }
}
