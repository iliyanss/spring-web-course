package com.example.pathfinder.service;

import com.example.pathfinder.model.dtos.UserRegistrationDto;
import com.example.pathfinder.model.entities.Role;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.model.entities.enums.UserRoles;
import com.example.pathfinder.repository.RoleRepository;
import com.example.pathfinder.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    public void registerUser(UserRegistrationDto userRegistrationDto){
        User user = modelMapper.map(userRegistrationDto, User.class);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        Role role = roleRepository.findByName(UserRoles.USER);
        user.getRoles().add(role);
        userRepository.save(user);

    }
}
