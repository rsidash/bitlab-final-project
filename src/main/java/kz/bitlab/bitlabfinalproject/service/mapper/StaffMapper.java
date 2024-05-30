package kz.bitlab.bitlabfinalproject.service.mapper;

import kz.bitlab.bitlabfinalproject.entity.Staff;
import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffDto;
import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper extends BaseMapper<StaffDto, Staff> {
    void updateFromDto(StaffUpdateDto dto, @MappingTarget Staff staff);
}
