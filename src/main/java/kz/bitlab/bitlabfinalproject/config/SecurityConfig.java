package kz.bitlab.bitlabfinalproject.config;

import kz.bitlab.bitlabfinalproject.service.UserService;
import kz.bitlab.bitlabfinalproject.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService()).passwordEncoder(passwordEncoder());

        http.authorizeHttpRequests(
                req -> req
                        .requestMatchers("/sign-in", "/sign-up").anonymous()
                        .requestMatchers("/sign-out", "/change-password", "/save-password").authenticated()
                        .requestMatchers("/profile").authenticated()
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/", "/teams", "/players", "/staff").permitAll()
                        .anyRequest().authenticated()
        );


        http.exceptionHandling(
                exception -> exception.accessDeniedPage("/forbidden")
        );

        http.formLogin(login -> login
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/"))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
                .csrf(AbstractHttpConfigurer::disable);

        http
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
