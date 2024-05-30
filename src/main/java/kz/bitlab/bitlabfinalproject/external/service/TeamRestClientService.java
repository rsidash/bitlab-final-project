package kz.bitlab.bitlabfinalproject.external.service;

import jakarta.annotation.Nullable;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;
import kz.bitlab.bitlabfinalproject.exception.NotAllowedException;
import kz.bitlab.bitlabfinalproject.external.client.TeamRestClient;
import kz.bitlab.bitlabfinalproject.service.UserService;
import kz.bitlab.bitlabfinalproject.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamRestClientService {
    private final TeamRestClient teamRestClient;
    private final UserService userService;
    private final UserMapper userMapper;

    public List<TeamDto> getAllTeams() {
        try {
            return teamRestClient.getAllTeams();
        } catch (HttpClientErrorException e) {
            log.error("Teams not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return Collections.emptyList();
    }

    public List<TeamDto> getAllUserTeams(@NonNull final Long userId) {
        try {
            return teamRestClient.getAllUserTeams(userId);
        } catch (HttpClientErrorException e) {
            log.error("Teams not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return Collections.emptyList();
    }

    @Nullable
    public TeamDataDto getTeamById(@NonNull final Long id) {
        try {
            return teamRestClient.getTeamById(id);
        } catch (HttpClientErrorException e) {
            log.error("Team not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return null;
    }

    @Nullable
    public TeamDataDto getTeamByUuid(@NonNull final String uuid) {
        try {
            return teamRestClient.getTeamByUuid(uuid);
        } catch (HttpClientErrorException e) {
            log.error("Team not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return null;
    }

    @Nullable
    public TeamDto createTeam(@NonNull final TeamDto teamDto) {
        final var user = userService.getCurrentUser();

        if (Objects.isNull(user)) {
            throw new NotAllowedException();
        }

        teamDto.setUser(userMapper.toDto(user));

        try {
            return teamRestClient.createTeam(teamDto);
        } catch (HttpClientErrorException e) {
            log.error(String.valueOf(e));
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return null;
    }

    @Nullable
    public TeamDto updateTeam(@NonNull final String uuid, @NonNull final TeamUpdateDto teamDto) {
        checkPermissionsByTeamUuid(uuid);

        try {
            return teamRestClient.updateTeam(uuid, teamDto);
        } catch (HttpClientErrorException e) {
            log.error(String.valueOf(e));
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return null;
    }

    public void deleteTeam(@NonNull final String uuid) {
        checkPermissionsByTeamUuid(uuid);

        try {
            teamRestClient.deleteTeam(uuid);
        } catch (HttpClientErrorException e) {
            log.error(String.valueOf(e));
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }
    }

    private void checkPermissionsByTeamUuid(@NonNull final String uuid) {
        final var user = userService.getCurrentUser();
        final var team = getTeamByUuid(uuid);

        if (Objects.isNull(team) || Objects.isNull(team.getUser())) {
            throw new RuntimeException("Team is null or team user is null");
        }

        if (!team.getUser().getId().equals(user.getId())) {
            throw new NotAllowedException();
        }
    }
}
