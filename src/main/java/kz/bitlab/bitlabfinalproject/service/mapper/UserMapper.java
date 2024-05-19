package kz.bitlab.bitlabfinalproject.service.mapper;

import kz.bitlab.bitlabfinalproject.entity.security.User;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "roles", source = "user.roles")
    UserDto toDto(User user);

    @Mapping(target = "firstName", source = "userDto.firstName")
    @Mapping(target = "lastName", source = "userDto.lastName")
    @Mapping(target = "roles", source = "userDto.roles")
    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);

    void updateFromDto(UserDto dto, @MappingTarget User user);
}
