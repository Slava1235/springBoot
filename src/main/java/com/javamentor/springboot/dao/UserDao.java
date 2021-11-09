package com.javamentor.springboot.dao;


import com.javamentor.springboot.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(Long id);

    public void removeUser(Long id);

    public User findByUserName(String username);
}
