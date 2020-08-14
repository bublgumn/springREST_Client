package org.client.springClient.service;

import org.client.springClient.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    User saveUser(User user);

    void updateUser(User user);

    void deleteById(Long id);


    User findByName(String tokenForUser);
}
