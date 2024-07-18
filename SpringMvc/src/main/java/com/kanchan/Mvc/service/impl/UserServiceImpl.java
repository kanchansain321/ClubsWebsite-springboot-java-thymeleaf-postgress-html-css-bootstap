package com.kanchan.Mvc.service.impl;

import com.kanchan.Mvc.dto.RegistrationDto;
import com.kanchan.Mvc.models.Role;
import com.kanchan.Mvc.models.UserEntity;
import com.kanchan.Mvc.repository.RoleRepository;
import com.kanchan.Mvc.repository.UserRepository;
import com.kanchan.Mvc.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
@Service
public class UserServiceImpl implements UserService {
private UserRepository userRepository;
private PasswordEncoder passwordEncoder;
private RoleRepository roleRepository;
@Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user= new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role=roleRepository.findByName("User");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
