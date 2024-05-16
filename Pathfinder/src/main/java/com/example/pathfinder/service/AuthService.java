package com.example.pathfinder.service;

import com.example.pathfinder.model.dtos.UserRegistrationDto;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public AuthService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void register(UserRegistrationDto userRegistrationDto){
        boolean passwordsMatch = userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword());
        if(!passwordsMatch) {
            throw new RuntimeException("Passwords do not match");
        }
        User byEmail = userRepository.findByEmail(userRegistrationDto.getEmail()).orElse(null);
        if(byEmail != null) {
            throw new RuntimeException("Email already in use");
        }
        User user = this.modelMapper.map(userRegistrationDto, User.class);
        this.userRepository.save(user);
    }
}
