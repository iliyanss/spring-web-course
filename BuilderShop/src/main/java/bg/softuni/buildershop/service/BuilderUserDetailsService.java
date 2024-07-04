package bg.softuni.buildershop.service;

import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.model.entity.UserRoleEntity;
import bg.softuni.buildershop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BuilderUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public BuilderUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .map(BuilderUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User" + username + " not found"));
    }
    private static UserDetails map(UserEntity userEntity) {
        return User
                .withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(userEntity
                        .getRoles()
                        .stream()
                        .map(BuilderUserDetailsService::map)
                        .toList())
                .build();
    }

    private static GrantedAuthority map(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority(
                "ROLE_" + userRoleEntity.getRole().name()
        );
    }
}
