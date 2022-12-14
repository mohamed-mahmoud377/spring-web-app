package com.example.demo.service.impl;

import com.example.demo.io.entity.UserEntity;
import com.example.demo.io.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("user already exists");

        UserEntity userEntity= new UserEntity();

        BeanUtils.copyProperties(user,userEntity);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        String publishUserId = utils.generateUserId(30);
        userEntity.setUserId(publishUserId);

        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto returnValue = new UserDto();

        BeanUtils.copyProperties(storedUserDetails,returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity==null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),new ArrayList<>());
    }
}
