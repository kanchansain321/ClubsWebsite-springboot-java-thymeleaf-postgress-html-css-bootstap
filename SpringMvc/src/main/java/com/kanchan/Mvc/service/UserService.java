package com.kanchan.Mvc.service;


import com.kanchan.Mvc.dto.RegistrationDto;
import com.kanchan.Mvc.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
