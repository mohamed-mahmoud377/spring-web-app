package com.example.demo.service;

import com.example.demo.shared.dto.UserDto;
import org.apache.catalina.User;

public interface UserService {

    UserDto createUser(UserDto user);
}
