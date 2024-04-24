package dw.gameshop.service;

import dw.gameshop.dto.UserDto;
import dw.gameshop.model.User;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        User user = new User(userDto.getUserId(),
                userDto.getUserName(),
                userDto.getUserEmail(),
                bCryptPasswordEncoder.encode(userDto.getPassword()),
                LocalDateTime.now());
        return userRepository.save(user).getUserId();
    }
}
