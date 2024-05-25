package kz.bitlab.bitlabfinalproject.service.impl;

import kz.bitlab.bitlabfinalproject.entity.Team;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;
import kz.bitlab.bitlabfinalproject.exception.NotFoundException;
import kz.bitlab.bitlabfinalproject.repository.TeamRepository;
import kz.bitlab.bitlabfinalproject.service.TeamService;
import kz.bitlab.bitlabfinalproject.service.UserService;
import kz.bitlab.bitlabfinalproject.service.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final UserService userService;

    @Transactional(readOnly = true)
    @Override
    public List<TeamDto> findAll() {
        final var teams = teamRepository.findAll();

        return teamMapper.toDtoList(teams);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TeamDto> findByOwner(@NonNull final Long userId) {
        final var teams = teamRepository.findAllByUserId(userId);

        return teamMapper.toDtoList(teams);
    }

    @Transactional(readOnly = true)
    @Override
    public TeamDataDto findById(@NonNull final Long id) {
        final var team = teamRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Team not found")
        );

        return teamMapper.toTeamDataDto(team);
    }

    @Transactional(readOnly = true)
    @Override
    public TeamDataDto findByUuid(@NonNull final String uuid) {
        final var team = teamRepository.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Team not found")
        );

        return teamMapper.toTeamDataDto(team);
    }

    @Override
    public Team findEntityByUuid(@NonNull final String uuid) {
        return teamRepository.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Team not found")
        );
    }

    @Transactional(readOnly = true)
    @Override
    public TeamDataDto findByName(@NonNull final String name) {
        final var team = teamRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Team not found")
        );

        return teamMapper.toTeamDataDto(team);
    }

    @Transactional
    @Override
    public TeamDto create(@NonNull final TeamDto teamDto) {
        // FIXME: add current user as team owner
        final var user = userService.getUserById(1L);

        final var team = teamMapper.toEntity(teamDto);
        team.setUser(user);

        final var savedTeam = teamRepository.save(team);

        return teamMapper.toDto(savedTeam);
    }

    @Transactional
    @Override
    public TeamDto update(@NonNull final String uuid, @NonNull final TeamUpdateDto teamDto) {
        final var existingTeam = teamRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Team not found"));

        teamMapper.updateFromDto(teamDto, existingTeam);

        final var updatedTeam = teamRepository.save(existingTeam);
        return teamMapper.toDto(updatedTeam);
    }

    @Transactional
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
