package com.ntn.service;

import com.ntn.dto.UserDTO;
import com.ntn.entity.EmailDetails;
import com.ntn.entity.User;
import com.ntn.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IEmailService emailService;

    @Override
    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.getById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void createAccount(User user) {
        userRepository.save(user);
        UserDTO userDTO = modelMapper.map(userRepository.findByUsername(user.getUsername()), UserDTO.class);
        String subject = "Activate your NShoes account";
        String msgBody = "Hi " + user.getUsername() + ",\n" +
                "\n" +
                "To activate your NShoes account, please click http://localhost:8080/api/v1/auth/verification?username=" + userDTO.getUsername() + "&id=" + userDTO.getUserId() + " to verify your email address and you’ll be prompted to login.\n" +
                "\n" +

                "Sincerely,\n" +
                "The NShoes Team";
        EmailDetails emailDetails = new EmailDetails(userDTO.getEmail(), msgBody, subject, "");

        String a = emailService.sendSimpleMail(emailDetails);
        System.out.println(a);
    }

    @Override
    public void updateAccount(User user) {
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        if (user == null) throw new UsernameNotFoundException(username);
        if (userDTO.getUserRole() != null) {
            return new org.springframework.security.core.userdetails.User(userDTO.getUsername(), userDTO.getPassword(), AuthorityUtils.createAuthorityList(userDTO.getUserRole().getRole()));
        } else
//            return new org.springframework.security.core.userdetails.User(userDTO.getUsername(), userDTO.getPassword(), AuthorityUtils.createAuthorityList("CUSTOMER"));
            return new org.springframework.security.core.userdetails.User(userDTO.getUsername(), userDTO.getPassword(), AuthorityUtils.createAuthorityList(null));

    }

}
