package kz.bitlab.bitlabfinalproject.external.service;

import jakarta.annotation.Nullable;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;
import kz.bitlab.bitlabfinalproject.external.client.TeamRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamRestClientService {
    private final TeamRestClient teamRestClient;

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
        try {
            teamRestClient.deleteTeam(uuid);
        } catch (HttpClientErrorException e) {
            log.error(String.valueOf(e));
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }
    }
}
