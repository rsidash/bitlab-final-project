package kz.bitlab.bitlabfinalproject.service;

import kz.bitlab.bitlabfinalproject.entity.security.User;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserCreateDto;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserUpdateDto;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserUpdatePasswordDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getCurrentUser();
    UserDto create(UserCreateDto userCreateDto);
    UserDto update(Long id, UserUpdateDto userUpdateDto);
    UserDto updatePassword(Long id, UserUpdatePasswordDto userUpdatePasswordDto);
    void delete(Long id);
    User getUserById(Long id);
}
