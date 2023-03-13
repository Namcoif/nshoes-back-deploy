package com.ntn.controller;

import com.ntn.dto.UserDTO;
import com.ntn.entity.Role;
import com.ntn.entity.User;
import com.ntn.service.IUserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    static JSONObject message = new JSONObject();

    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

//    @GetMapping
//    public List<User> getListUsers(){
//        List<User> users = userService.getListUsers();
//        return users;
//    }

    @GetMapping
    public ResponseEntity<?> getListUsers() throws JSONException {
        message = new JSONObject();
        try {
            List<User> users = userService.getListUsers();
            List<UserDTO> userDTOS = modelMapper.map(users, new TypeToken<List<UserDTO>>() {
            }.getType());

            return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
        } catch (Exception e) {
            message.put("resultText", "Get list user FAIL!");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.toString());
        }

    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> findUserByID(@PathParam("id") String id) throws JSONException {
        message = new JSONObject();
        try {
            User user = userService.getUserById(id);
            UserDTO userDTO = modelMapper.map(user, new TypeToken<UserDTO>() {
            }.getType());

            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (Exception e) {
            message.put("resultText", "Get user FAIL!");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.toString());
        }

    }

    @PutMapping(value = "/admin/update/{id}")
    public ResponseEntity<?> updateUserForAdmin(@PathVariable String id, @RequestBody UserDTO userDTO) throws JSONException {
        message = new JSONObject();

        try {

            User user = modelMapper.map(userDTO, User.class);
            user.setUserId(id);

            userService.updateUser(user);

            message.put("resultText", "Update user successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(message.toString());
        } catch (Exception e) {
            message.put("resultText", "Update user FAIL!");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(message.toString());
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateUserForCustomer(@PathVariable String id, @RequestBody UserDTO userDTO) throws JSONException {
        message = new JSONObject();

        User user = userService.getUserById(id);
        if (user == null) {
            message.put("resultText", "User not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.toString());
        }

        try {
            User userUpdate = modelMapper.map(userDTO, User.class);
            Role role = modelMapper.map(user.getRole(), Role.class);

            userUpdate.setUserId(id);
            userUpdate.setStatus(user.getStatus());
            userUpdate.setRole(role);

            userService.updateUser(userUpdate);

            message.put("resultText", "Update user successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(message.toString());
        } catch (Exception e) {

            message.put("resultText", "Update user FAIL!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.toString());

        }
    }


}
