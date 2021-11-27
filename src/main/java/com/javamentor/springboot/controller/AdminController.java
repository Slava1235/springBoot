package com.javamentor.springboot.controller;

import com.javamentor.springboot.model.Role;
import com.javamentor.springboot.model.User;
import com.javamentor.springboot.service.RoleService;
import com.javamentor.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "save")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        Set<Role> userRole = user.getRoles();
        user.setRoles(userRole);
        userService.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "edit")
    public ResponseEntity<Void> edit(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
