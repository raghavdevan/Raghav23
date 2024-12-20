package com.hmsapp.hmsapp.service;

import com.hmsapp.hmsapp.entity.User;
import com.hmsapp.hmsapp.payload.LoginDto;
import com.hmsapp.hmsapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private JWTService  jwsService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String verifyLogin(LoginDto login){
        Optional<User> opUser = userRepository.findByUsername(login.getUsername());
        if(opUser.isPresent()){

            User user = opUser.get();
            if (BCrypt.checkpw(login.getPassword(), user.getPassword())){
                String token = jwsService.generateToken(user.getUsername());
                return token;
            }

        }else {
            return null;
        }
            return null;
    }
}
