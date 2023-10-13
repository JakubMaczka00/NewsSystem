package com.example.news_system.service;

import com.example.news_system.component.Role;
import com.example.news_system.config.jwt.JwtService;
import com.example.news_system.constant.Message;
import com.example.news_system.dto.request.LoginReqDTO;
import com.example.news_system.dto.request.RegisterReqDTO;
import com.example.news_system.dto.response.auth.AuthResDTO;
import com.example.news_system.entity.User;
import com.example.news_system.repository.UserRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResDTO register(RegisterReqDTO registerReqDTO){
        checkIfUserExists(registerReqDTO.getEmail());
        isPasswordValid(registerReqDTO.getPassword());
        User user = User.builder()
                .firstname(registerReqDTO.getFirstname())
                .lastname(registerReqDTO.getLastname())
                .email(registerReqDTO.getEmail())
                .password(passwordEncoder.encode(registerReqDTO.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResDTO.builder()
                .token(jwtToken)
                .build();

    }



    public AuthResDTO login(LoginReqDTO loginReqDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginReqDTO.getEmail(),
                        loginReqDTO.getPassword()
                )
        );
        var user = userRepository.findByEmail(loginReqDTO.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResDTO.builder()
                .token(jwtToken)
                .build();

    }
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElse(null);
        return user;
    }

    private void checkIfUserExists(String email){
        boolean isExist = userRepository.existByEmail(email);
        Preconditions.checkArgument(!isExist, Message.USER_EXISTS);
    }

    public void isPasswordValid(String password) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        boolean passwordMatches = password.matches(passwordPattern);
        Preconditions.checkArgument(passwordMatches, Message.PASSWORD_DOES_NOT_MEET_REQUIREMENTS);
    }
    public void isUserRole(User user){
        String userRole = user.getRole().name();
        Preconditions.checkArgument(userRole.equals("USER"), Message.USER_ROLE_REQUIRED);
    }
    public void isAdminRole(User user){
        String userRole = user.getRole().name();
        Preconditions.checkArgument(userRole.equals("ADMIN"), Message.ADMIN_ROLE_REQUIRED);
    }
}
