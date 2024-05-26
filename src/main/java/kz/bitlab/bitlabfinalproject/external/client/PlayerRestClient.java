package kz.bitlab.bitlabfinalproject.external.client;

import jakarta.annotation.Nullable;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerDto;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerUpdateDto;
import kz.bitlab.bitlabfinalproject.enums.PlayingPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PlayerRestClient {
    private final RestTemplate restTemplate;

    @Value("${api.url}")
    private String url;

    public List<PlayerDto> getAllPlayers(final String team, final PlayingPosition position) {
        final var players = restTemplate.getForObject(
                url + "/players?team={team}&position={position}",
                PlayerDto[].class, team, position
        );

        if (Objects.nonNull(players)) {
            return Arrays.asList(players);
        } else {
            return Collections.emptyList();
        }
    }

    public List<PlayerDto> getAllTeamPlayers(@NonNull final String teamName) {
        final var players = restTemplate.getForObject(
                url + "/players/by-team/{teamName}",
                PlayerDto[].class, teamName
        );

        if (Objects.nonNull(players)) {
            return Arrays.asList(players);
        } else {
            return Collections.emptyList();
        }
    }

    @Nullable
    public PlayerDto getPlayerByUuid(@NonNull final String uuid) {
        return restTemplate.getForObject(
                url + "/players/{uuid}",
                PlayerDto.class, uuid
        );
    }

    public void createPlayer(@NonNull final PlayerDto playerDto, @NonNull final String teamUuid) {
        restTemplate.postForObject(url + "/players/add/{uuid}", playerDto, PlayerDto.class, teamUuid);
    }

    public void updatePlayer(@NonNull final String uuid, @NonNull final PlayerUpdateDto playerDto) {
        restTemplate.postForObject(url + "/players/update/{uuid}", playerDto, PlayerDto.class, uuid);
    }

    public void deletePlayer(@NonNull final String uuid) {
        restTemplate.delete(url + "/players/{uuid}", uuid);
    }
}
