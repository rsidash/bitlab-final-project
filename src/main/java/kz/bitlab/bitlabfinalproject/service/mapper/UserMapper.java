package kz.bitlab.bitlabfinalproject.service.mapper;

import kz.bitlab.bitlabfinalproject.entity.security.User;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, User> {
    void updateFromDto(UserDto dto, @MappingTarget User user);
}
