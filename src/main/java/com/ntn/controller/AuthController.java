package com.ntn.controller;

import com.ntn.dto.JwtTokenDTO;
import com.ntn.dto.SignInDTO;
import com.ntn.dto.SignUpDTO;
import com.ntn.entity.User;
import com.ntn.service.IRoleService;
import com.ntn.service.IUserService;
import com.ntn.utils.JwtUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtUtils jwtUtils;

    private JSONObject message = new JSONObject();

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInDTO signInDTO) throws JSONException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInDTO.getUsername(), signInDTO.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = jwtUtils.ganerateJwtToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            User user = userService.findByUsername(signInDTO.getUsername());

            return ResponseEntity.ok(
                    new JwtTokenDTO(userDetails.getUsername(), jwtToken, userDetails.getAuthorities().toString(), user.getUserId())
            );
        } catch (Exception e) {
            message = new JSONObject();
            message.put("auth", "Your account or password is incorrect!");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message.toString());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDTO signUpDTO) throws JSONException {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String enCrypPassword = bCryptPasswordEncoder.encode(signUpDTO.getPassword());
            User user = modelMapper.map(signUpDTO, User.class);
            user.setPassword(enCrypPassword);
            user.setStatus(User.AccountStatus.NOT_ACTIVE);
            userService.createAccount(user);
            return ResponseEntity.ok("Register successfully!");
        } catch (Exception e) {
            message = new JSONObject();
            message.put("auth", "Username or Email is exist!");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message.toString());
//            throw e;
        }
    }

    @GetMapping("/verification")
    public ResponseEntity<?> accountVerification(@PathParam("username") String username, @PathParam("id") String id) {
        User user = new User();
        try {
            user = userService.getUserById(id);
            user.setStatus(User.AccountStatus.ACTIVE);
            user.setRole(roleService.getRoleById(1));

            userService.updateUser(user);
            return ResponseEntity.ok("Account Verification Successful!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cannot found your Account!");

        }

    }
}
