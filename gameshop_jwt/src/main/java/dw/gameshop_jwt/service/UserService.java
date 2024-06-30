package dw.gameshop_jwt.service;

import dw.gameshop_jwt.dto.UserDto;
import dw.gameshop_jwt.model.Authority;
import dw.gameshop_jwt.model.User;
import dw.gameshop_jwt.repository.UserRepository;
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
