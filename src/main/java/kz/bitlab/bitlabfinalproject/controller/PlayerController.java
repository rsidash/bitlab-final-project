package kz.bitlab.bitlabfinalproject.controller;

import jakarta.validation.Valid;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerDto;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerUpdateDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.enums.PlayingPosition;
import kz.bitlab.bitlabfinalproject.external.service.PlayerRestClientService;
import kz.bitlab.bitlabfinalproject.external.service.TeamRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {
    private final PlayerRestClientService playerRestClientService;
    private final TeamRestClientService teamRestClientService;

    @GetMapping
    public String getAllPlayers(@RequestParam(value = "team", required = false) final String team,
                                @RequestParam(value = "playingPosition", required = false) final PlayingPosition position,
                                Model model) {
        final var players = playerRestClientService.getAllPlayers(team, position);
        model.addAttribute("players", players);

        final var teamNames = teamRestClientService.getAllTeams().stream()
                .map(TeamDto::getName).toList();
        model.addAttribute("teamNames", teamNames);

        return "player/index";
    }

    @GetMapping("/add/{teamUuid}")
    @PreAuthorize("isAuthenticated()")
    public String showCreatePage(@PathVariable("teamUuid") @NonNull final String teamUuid, Model model) {
        model.addAttribute("teamUuid", teamUuid);

        return "player/create";
    }

    @PostMapping("/add/{teamUuid}")
    @PreAuthorize("isAuthenticated()")
    public String create(@ModelAttribute @Valid PlayerDto playerDto, BindingResult result,
                         Model model, @PathVariable("teamUuid") @NonNull final String teamUuid) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "player/create";
        }

        playerRestClientService.createPlayer(playerDto, teamUuid);
        return "redirect:/teams/" + teamUuid;
    }

    @GetMapping("/edit/{uuid}")
    @PreAuthorize("isAuthenticated()")
    public String showEditPage(Model model, @PathVariable("uuid") @NonNull final String uuid) {
        PlayerDto player = playerRestClientService.getPlayerByUuid(uuid);
        model.addAttribute("player", player);

        return "player/edit";
    }

    @PostMapping("/edit/{uuid}")
    @PreAuthorize("isAuthenticated()")
    public String edit(@PathVariable("uuid") String uuid, @ModelAttribute PlayerUpdateDto playerUpdateDto,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("player", playerUpdateDto);
            return "player/edit";
        }

        final var playerDto = playerRestClientService.getPlayerByUuid(uuid);

        playerRestClientService.updatePlayer(uuid, playerUpdateDto);

        return Objects.nonNull(playerDto) && Objects.nonNull(playerDto.getTeam()) ?
                "redirect:/teams/" + playerDto.getTeam().getUuid() : "redirect:/teams";
    }

    @PostMapping("/delete/{uuid}")
    @PreAuthorize("isAuthenticated()")
    public String delete(@PathVariable("uuid") @NonNull final String uuid) {
        final var playerDto = playerRestClientService.getPlayerByUuid(uuid);

        playerRestClientService.deletePlayer(uuid);

        return Objects.nonNull(playerDto) && Objects.nonNull(playerDto.getTeam()) ?
                "redirect:/teams/" + playerDto.getTeam().getUuid() : "redirect:/teams";
    }
}
