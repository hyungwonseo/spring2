package com.dw.demo.service;

import com.dw.demo.dto.UserDto;
import com.dw.demo.model.Authority;
import com.dw.demo.model.User;
import com.dw.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String saveUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUserId(userDto.getUserId());
        if (userOptional.isPresent()) {
            return "이미 등록된 ID입니다";
        }
        Authority authority = new Authority();
        authority.setAuthorityName("ROLE_USER");
        User user = new User(userDto.getUserId(),
                userDto.getUserName(),
                userDto.getUserEmail(),
                bCryptPasswordEncoder.encode(userDto.getPassword()),
                authority,
                LocalDateTime.now());
        return userRepository.save(user).getUserId();
    }
}
