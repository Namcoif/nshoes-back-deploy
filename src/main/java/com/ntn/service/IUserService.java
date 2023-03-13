package com.ntn.service;

import com.ntn.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> getListUsers();

    void updateUser(User user);

    User getUserById(String id);
    User findByUsername(String username);

    void createAccount(User user);
    void updateAccount(User user);
}
