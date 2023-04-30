package com.ntn.service;

import com.ntn.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> getListUsers();

    Page<User> getListUsersPaging(Pageable pageable);

    void updateUser(User user);

    User getUserById(String id);

    User findByUsername(String username);

    void createAccount(User user);

    void updateAccount(User user);
}
