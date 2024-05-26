package kz.bitlab.bitlabfinalproject.service;

import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffDto;
import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffUpdateDto;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;

import java.util.List;

public interface StaffService {
    List<StaffDto> findAll();
    List<StaffDto> findByTeamName(String teamName);
    List<StaffDto> findByTeamNameAndJobTitle(String teamName, JobTitle jobTitle);
    StaffDto findByUuid(String uuid);
    void create(StaffDto staffDto, String teamUuid);
    void update(String uuid, StaffUpdateDto staffDto);
    void delete(String uuid);
}
