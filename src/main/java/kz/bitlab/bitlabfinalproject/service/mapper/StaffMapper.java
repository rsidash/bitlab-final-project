package kz.bitlab.bitlabfinalproject.service.mapper;

import kz.bitlab.bitlabfinalproject.entity.Staff;
import kz.bitlab.bitlabfinalproject.entity.dto.StaffDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    StaffDto toDto(Staff staff);

    Staff toEntity(StaffDto staffDto);

    List<StaffDto> toDtoList(List<Staff> staffList);

    void updateFromDto(StaffDto dto, @MappingTarget Staff staff);
}
