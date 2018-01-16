package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;

public interface IUserService {

    User findById(long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    void deleteAllUsers();

    boolean isUserExist(User user);
}
