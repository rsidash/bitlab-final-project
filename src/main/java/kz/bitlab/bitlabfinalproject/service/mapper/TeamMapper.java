package kz.bitlab.bitlabfinalproject.service.mapper;

import kz.bitlab.bitlabfinalproject.entity.Team;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamDto toDto(Team team);

    TeamDataDto toTeamDataDto(Team team);

    Team toEntity(TeamDto teamDto);

    Team toEntityFromTeamDataDto(TeamDataDto teamDataDto);

    List<TeamDto> toDtoList(List<Team> teams);

    void updateFromDto(TeamUpdateDto dto, @MappingTarget Team team);
}
