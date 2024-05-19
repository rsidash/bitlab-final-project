package kz.bitlab.bitlabfinalproject.external.client;

import jakarta.annotation.Nullable;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class TeamRestClient  {
    private final RestTemplate restTemplate;

    @Value("${api.url}")
    private String url;

    public List<TeamDto> getAllTeams() {
        final var teams = restTemplate.getForObject(
                url + "/teams",
                TeamDto[].class
        );

        if (Objects.nonNull(teams)) {
            return Arrays.asList(teams);
        } else {
            return Collections.emptyList();
        }
    }

    public List<TeamDto> getAllUserTeams(@NonNull final Long userId) {
        final var teams = restTemplate.getForObject(
                url + "/teams?userId={userId}",
                TeamDto[].class, userId
        );

        if (Objects.nonNull(teams)) {
            return Arrays.asList(teams);
        } else {
            return Collections.emptyList();
        }
    }

    @Nullable
    public TeamDataDto getTeamById(@NonNull final Long id) {
        return restTemplate.getForObject(
                url + "/teams?id={id}",
                TeamDataDto.class, id
        );
    }

    @Nullable
    public TeamDataDto getTeamByUuid(@NonNull final String uuid) {
        return restTemplate.getForObject(
                url + "/teams/{uuid}",
                TeamDataDto.class, uuid
        );
    }

    public TeamDto createTeam(@NonNull final TeamDto teamDto) {
        return restTemplate.postForObject(url + "/teams", teamDto, TeamDto.class);
    }

    public TeamDto updateTeam(@NonNull final String uuid, @NonNull final TeamUpdateDto teamDto) {
        return restTemplate.postForObject(url + "/teams/update/{uuid}", teamDto, TeamDto.class, uuid);
    }

    public void deleteTeam(@NonNull final String uuid) {
        restTemplate.delete(url + "/teams/{uuid}", uuid);
    }
}
