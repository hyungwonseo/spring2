package dw.gameshop_jwt.controller;

import dw.gameshop_jwt.dto.BaseResponse;
import dw.gameshop_jwt.dto.SessionDto;
import dw.gameshop_jwt.dto.UserDto;
import dw.gameshop_jwt.enumstatus.ResultCode;
import dw.gameshop_jwt.service.UserDetailService;
import dw.gameshop_jwt.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private UserDetailService userDetailService;
    private AuthenticationManager authenticationManager;
    private HttpServletRequest httpServletRequest;

    public UserController(UserService userService, UserDetailService userDetailService, AuthenticationManager authenticationManager, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.userDetailService = userDetailService;
        this.authenticationManager = authenticationManager;
        this.httpServletRequest = httpServletRequest;
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<String>> signUp(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        userService.saveUser(userDto),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<Void>> login(@RequestBody UserDto userDto,
                                        HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUserId(), userDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 세션 생성
        HttpSession session = request.getSession(true); // true: 세션이 없으면 새로 생성
        // 세션에 인증 객체 저장
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        null,
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<String>> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        "You have been logged out.",
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<BaseResponse<SessionDto>> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        SessionDto sessionDto = new SessionDto();
        sessionDto.setUserId(authentication.getName());
        sessionDto.setAuthority(authentication.getAuthorities());

        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        sessionDto,
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }
}
