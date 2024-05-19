package kz.bitlab.bitlabfinalproject.service;

import kz.bitlab.bitlabfinalproject.entity.dto.StaffDto;

import java.util.List;

public interface StaffService {
    List<StaffDto> findAll();
    List<StaffDto> findByTeamId(Long teamId);
    StaffDto findById(Long id);
    void create(StaffDto staffDto);
    void update(Long id, StaffDto staffDto);
    void delete(Long id);
}
