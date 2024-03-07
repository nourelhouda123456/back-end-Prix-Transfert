package com.example.demo.services;
 
 
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;


 
import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.CredentialsDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exceptions.AppException;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.user;
import com.example.demo.repositories.userRepository;

import jakarta.transaction.Transactional;
 

@RequiredArgsConstructor
@Service
public class UserService {
 	@Autowired
    private  userRepository userRepository;
 	@Autowired
    private  PasswordEncoder passwordEncoder;
 	@Autowired
    private  UserMapper userMapper;
 	@Autowired
    public UserService(com.example.demo.repositories.userRepository userRepository, PasswordEncoder passwordEncoder,
			UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}

	public UserDto login(CredentialsDto credentialsDto) {
        user user = userRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<user> optionalUser = userRepository.findByLogin(userDto.login());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        user user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        user savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByLogin(String login) {
        user user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

}