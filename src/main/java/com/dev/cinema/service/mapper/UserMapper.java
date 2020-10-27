package com.dev.cinema.service.mapper;

import com.dev.cinema.dto.UserResponseDto;
import com.dev.cinema.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        return userResponseDto;
    }
}
