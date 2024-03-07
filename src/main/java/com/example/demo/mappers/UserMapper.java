package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserDto;
import com.example.demo.models.user;
 

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(user user);

    @Mapping(target = "password", ignore = true)
    user signUpToUser(SignUpDto signUpDto);
}
