package kz.bitlab.bitlabfinalproject.service.mapper;

import kz.bitlab.bitlabfinalproject.entity.Team;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    @Mapping(source = "name", target = "name")
    TeamDto toDto(Team team);

    @Mapping(source = "name", target = "name")
    TeamDataDto toTeamDataDto(Team team);

    Team toEntity(TeamDto teamDto);

    Team toEntityFromTeamDataDto(TeamDataDto teamDataDto);

    List<TeamDto> toDtoList(List<Team> teams);

    void updateFromDto(TeamUpdateDto dto, @MappingTarget Team team);
}
