package bg.softuni.buildershop.Init;

import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.model.entity.UserRoleEntity;
import bg.softuni.buildershop.model.enums.UserRoleEnum;
import bg.softuni.buildershop.repository.UserRepository;
import bg.softuni.buildershop.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeed implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeed(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0){
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setRole(UserRoleEnum.ADMIN);
            userRoleRepository.save(userRoleEntity);
            UserRoleEntity userRoleEntity2 = new UserRoleEntity();
            userRoleEntity2.setRole(UserRoleEnum.USER);
            userRoleRepository.save(userRoleEntity2);
        }
        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = this.userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserEntity admin = new UserEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setEmail("admin@gmail.com");
            admin.getRoles().add(adminRole);
            userRepository.save(admin);
        }
    }
}
