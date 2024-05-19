package kz.bitlab.bitlabfinalproject.service.impl;

import jakarta.annotation.Nullable;
import kz.bitlab.bitlabfinalproject.entity.security.Role;
import kz.bitlab.bitlabfinalproject.entity.security.User;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserCreateDto;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserUpdateDto;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserUpdatePasswordDto;
import kz.bitlab.bitlabfinalproject.exception.InvalidPasswordException;
import kz.bitlab.bitlabfinalproject.exception.NotFoundException;
import kz.bitlab.bitlabfinalproject.repository.security.RoleRepository;
import kz.bitlab.bitlabfinalproject.repository.security.UserRepository;
import kz.bitlab.bitlabfinalproject.service.UserService;
import kz.bitlab.bitlabfinalproject.service.mapper.UserMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    //    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(@NonNull final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("User Not Found")
        );
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User Not Found")
        );
    }

//    @Nullable
//    public UserDto getCurrentUser() {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication instanceof AnonymousAuthenticationToken) {
//            return null;
//        }
//
//        return (UserDto) authentication.getPrincipal();
//    }
//
//    @Override
//    public UserDto create(@NonNull final UserCreateDto userCreateDto) {
//        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
//            throw new RuntimeException("User already exists");
//        }
//
//        if (userCreateDto.getPassword().equals(userCreateDto.getRePassword())) {
//            throw new InvalidPasswordException("Password not the same");
//        }
//
//        Role role = roleRepository.findByName("ROLE_USER");
//
//        User newUser = new User();
//        newUser.setUsername(userCreateDto.getUsername());
//        newUser.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
//        newUser.setFirstName(userCreateDto.getFirstName());
//        newUser.setLastName(userCreateDto.getLastName());
//        newUser.setRoles(Collections.singleton(role));
//
//        userRepository.save(newUser);
//        return userMapper.toDto(newUser);
//    }
//
//    @Override
//    public UserDto update(@NonNull final Long id, @NonNull final UserUpdateDto userUpdateDto) {
//        final var user = userRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//
//        final var firstName = userUpdateDto.getFirstName();
//        final var lastName = userUpdateDto.getLastName();
//
//        if (Objects.nonNull(firstName)) {
//            user.setFirstName(firstName);
//        }
//
//        if (Objects.nonNull(lastName)) {
//            user.setLastName(lastName);
//        }
//
//        userRepository.save(user);
//
//        return userMapper.toDto(user);
//    }
//
//    @Override
//    public UserDto updatePassword(@NonNull final Long id, @NonNull final UserUpdatePasswordDto userUpdatePasswordDto) {
//        final var user = userRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//
//        if (!passwordEncoder.matches(userUpdatePasswordDto.getOldPassword(), user.getPassword())) {
//            throw new InvalidPasswordException("Old password is incorrect");
//        }
//
//        user.setPassword(passwordEncoder.encode(userUpdatePasswordDto.getNewPassword()));
//        userRepository.save(user);
//
//        return userMapper.toDto(user);
//    }
//
//    @Override
//    public void delete(@NonNull final Long id) {
//        final var user = userRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//
//        userRepository.delete(user);
//    }
}
