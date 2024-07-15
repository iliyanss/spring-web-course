package bg.softuni.mobilele.config;

import bg.softuni.mobilele.repositories.UserRepository;
import bg.softuni.mobilele.services.MobileleUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

     return
             http
                .authorizeHttpRequests(
                        authorizeRequest -> {

                            authorizeRequest
                                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                    .permitAll()
                                    .requestMatchers("/", "/home", "/users/login", "/users/register")
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                     .formLogin(
                             formLogin -> {
                                 formLogin
                                         .loginPage("/users/login")
                                         .usernameParameter("email")
                                         .passwordParameter("password")
                                         .defaultSuccessUrl("/", true)
                                         .failureForwardUrl("/users/login-error");
                             }
                     )
                     .logout(
                             logout -> {
                                 logout
                                         .logoutUrl("/users/logout")
                                         .logoutSuccessUrl("/home")
                                         .invalidateHttpSession(true);
                             }
                     ).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public MobileleUserDetailsService userDetailsService(UserRepository userRepository){
        return new MobileleUserDetailsService(userRepository);
    }
}
