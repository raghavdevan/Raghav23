package com.hmsapp.hmsapp.controller;

import com.hmsapp.hmsapp.entity.User;
import com.hmsapp.hmsapp.payload.LoginDto;
import com.hmsapp.hmsapp.repository.UserRepository;
import com.hmsapp.hmsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginContext;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class Authcontroller {
    private UserRepository userRepository;
    private UserService userService;
    private LoginDto loginDto;

    public Authcontroller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody User user ) {


        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> opMobile = userRepository.findByMobile(user.getMobile());
        if (opMobile.isPresent()) {
            return new ResponseEntity<>("Mobile already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }
    @PostMapping ("/login")
    public ResponseEntity<?>login(@RequestBody LoginDto login)

    {

        String token = userService.verifyLogin(loginDto);
        if (token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("invalid", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
