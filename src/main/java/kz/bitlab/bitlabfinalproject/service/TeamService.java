package kz.bitlab.bitlabfinalproject.service;

import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;

import java.util.List;

public interface TeamService {
    List<TeamDto> findAll();
    List<TeamDto> findByOwner(Long userId);
    TeamDataDto findById(Long id);
    TeamDataDto findByUuid(String uuid);
    TeamDataDto findByName(String name);
    TeamDto create(TeamDto teamDto);
    TeamDto update(String uuid, TeamUpdateDto teamDto);
    void delete(String uuid);
}
