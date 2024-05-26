package kz.bitlab.bitlabfinalproject.controller.rest;

import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerDto;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerUpdateDto;
import kz.bitlab.bitlabfinalproject.enums.PlayingPosition;
import kz.bitlab.bitlabfinalproject.exception.NotFoundException;
import kz.bitlab.bitlabfinalproject.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlayerRestController {
    private final PlayerService playerService;

    @GetMapping("/players")
    public List<PlayerDto> findAll(@RequestParam(value = "team", required = false) final String team,
                                   @RequestParam(value = "position", required = false) final PlayingPosition position) {
        return playerService.findByTeamNameAndPosition(team, position);
    }

    @GetMapping("/players/by-team/{team}")
    public List<PlayerDto> findByTeam(@PathVariable("team") @NonNull final String teamName) {
        return playerService.findByTeamName(teamName);
    }

    @GetMapping("/players/{uuid}")
    public PlayerDto findByUuid(@PathVariable("uuid") @NonNull final String uuid) {
        try {
            return playerService.findByUuid(uuid);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/players/add/{teamUuid}")
    public void create(@PathVariable("teamUuid") @NonNull final String teamUuid,
                       @RequestBody @NonNull final PlayerDto playerDto) {
        playerService.create(playerDto, teamUuid);
    }

    @PostMapping("/players/update/{uuid}")
    public void update(@PathVariable("uuid") @NonNull final String uuid,
                       @RequestBody @NonNull final PlayerUpdateDto playerUpdateDto) {
        try {
            playerService.update(uuid, playerUpdateDto);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/players/{uuid}")
    public void delete(@PathVariable("uuid") @NonNull String uuid) {
        try {
            playerService.delete(uuid);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
