package kz.bitlab.bitlabfinalproject.external.service;

import jakarta.annotation.Nullable;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerDto;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerUpdateDto;
import kz.bitlab.bitlabfinalproject.enums.PlayingPosition;
import kz.bitlab.bitlabfinalproject.external.client.PlayerRestClient;
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
public class PlayerRestClientService {
    private final PlayerRestClient playerRestClient;

    public List<PlayerDto> getAllPlayers(final String team, final PlayingPosition position) {
        try {
            return playerRestClient.getAllPlayers(team, position);
        } catch (HttpClientErrorException e) {
            log.error("Players not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return Collections.emptyList();
    }

    public List<PlayerDto> getAllTeamPlayers(@NonNull final String teamName) {
        try {
            return playerRestClient.getAllTeamPlayers(teamName);
        } catch (HttpClientErrorException e) {
            log.error("Players not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return Collections.emptyList();
    }

    @Nullable
    public PlayerDto getPlayerByUuid(@NonNull final String uuid) {
        try {
            return playerRestClient.getPlayerByUuid(uuid);
        } catch (HttpClientErrorException e) {
            log.error("Player not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return null;
    }

    public void createPlayer(@NonNull final PlayerDto playerDto, @NonNull final String teamUuid) {
        try {
            playerRestClient.createPlayer(playerDto, teamUuid);
        } catch (HttpClientErrorException e) {
            log.error(String.valueOf(e));
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }
    }

    public void updatePlayer(@NonNull final String uuid, @NonNull final PlayerUpdateDto playerUpdateDto) {
        try {
            playerRestClient.updatePlayer(uuid, playerUpdateDto);
        } catch (HttpClientErrorException e) {
            log.error(String.valueOf(e));
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }
    }

    public void deletePlayer(@NonNull final String uuid) {
        try {
            playerRestClient.deletePlayer(uuid);
        } catch (HttpClientErrorException e) {
            log.error(String.valueOf(e));
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }
    }
}
