package kz.bitlab.bitlabfinalproject.service.mapper;

import kz.bitlab.bitlabfinalproject.entity.Staff;
import kz.bitlab.bitlabfinalproject.entity.dto.StaffDto;
import kz.bitlab.bitlabfinalproject.entity.security.User;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    StaffDto toDto(Staff staff);

    Staff toEntity(StaffDto staffDto);

    List<StaffDto> toDtoList(List<Staff> staffList);

    void updateFromDto(StaffDto dto, @MappingTarget Staff staff);
}
