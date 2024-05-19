package kz.bitlab.bitlabfinalproject.controller.rest;

import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;
import kz.bitlab.bitlabfinalproject.exception.NotFoundException;
import kz.bitlab.bitlabfinalproject.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeamRestController {
    private final TeamService teamService;

    @GetMapping("/teams")
    public List<TeamDto> findAll() {
        return teamService.findAll();
    }

    @GetMapping("/teams/by-owner")
    public List<TeamDto> findByOwnerId(@RequestParam("userId") @NonNull final Long ownerId) {
        return teamService.findByOwner(ownerId);
    }

    @GetMapping("/teams/by-id")
    public TeamDataDto findById(@RequestParam("id") @NonNull final Long id) {
        try {
            return teamService.findById(id);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teams/{uuid}")
    public TeamDataDto findByUuid(@PathVariable("uuid") @NonNull final String uuid) {
        try {
            return teamService.findByUuid(uuid);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teams/by-name")
    public TeamDataDto findByName(@RequestParam("name") @NonNull final String name) {
        try {
            return teamService.findByName(name);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teams")
    public TeamDto create(@RequestBody @NonNull final TeamDto teamDto) {
        return teamService.create(teamDto);
    }

    @PostMapping("/teams/update/{uuid}")
    public TeamDto update(@PathVariable("uuid") @NonNull final String uuid, @RequestBody @NonNull final TeamUpdateDto teamDto) {
        try {
            return teamService.update(uuid, teamDto);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/teams/{uuid}")
    public void deleteTeam(@PathVariable("uuid") @NonNull String uuid){
        try {
            teamService.delete(uuid);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
