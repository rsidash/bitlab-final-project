package kz.bitlab.bitlabfinalproject.service.impl;

import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;
import kz.bitlab.bitlabfinalproject.exception.NotFoundException;
import kz.bitlab.bitlabfinalproject.repository.TeamRepository;
import kz.bitlab.bitlabfinalproject.service.TeamService;
import kz.bitlab.bitlabfinalproject.service.UserService;
import kz.bitlab.bitlabfinalproject.service.mapper.TeamMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final UserService userService;

    @Override
    public List<TeamDto> findAll() {
        final var teams = teamRepository.findAll();

        return teamMapper.toDtoList(teams);
    }

    @Override
    public List<TeamDto> findByOwner(@NonNull final Long userId) {
        final var teams = teamRepository.findAllByUserId(userId);

        return teamMapper.toDtoList(teams);
    }

    @Override
    public TeamDataDto findById(@NonNull final Long id) {
        final var team = teamRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Team not found")
        );

        return teamMapper.toTeamDataDto(team);
    }

    @Override
    public TeamDataDto findByUuid(@NonNull final String uuid) {
        final var team = teamRepository.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Team not found")
        );

        return teamMapper.toTeamDataDto(team);
    }

    @Override
    public TeamDataDto findByName(@NonNull final String name) {
        final var team = teamRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Team not found")
        );

        return teamMapper.toTeamDataDto(team);
    }

    @Override
    public TeamDto create(@NonNull final TeamDto teamDto) {
        // FIXME: add current user as team owner
        final var user = userService.getUserById(1L);

        final var team = teamMapper.toEntity(teamDto);
        team.setUser(user);

        final var savedTeam = teamRepository.save(team);

        return teamMapper.toDto(savedTeam);
    }

    @Override
    public TeamDto update(@NonNull final String uuid, @NonNull final TeamUpdateDto teamDto) {
        final var existingTeam = teamRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Team not found"));

        teamMapper.updateFromDto(teamDto, existingTeam);

        final var updatedTeam = teamRepository.save(existingTeam);
        return teamMapper.toDto(updatedTeam);
    }

    @Override
    public void delete(@NonNull final String uuid) {
        teamRepository.findByUuid(uuid).ifPresentOrElse(
                team -> teamRepository.deleteById(team.getId()),
                () -> {
                    throw new NotFoundException("Team not found");
                }
        );
    }
}
