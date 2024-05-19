package kz.bitlab.bitlabfinalproject.controller;

import jakarta.validation.Valid;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerDto;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerUpdateDto;
import kz.bitlab.bitlabfinalproject.external.service.PlayerRestClientService;
import kz.bitlab.bitlabfinalproject.external.service.TeamRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {
    private final PlayerRestClientService playerRestClientService;
    private final TeamRestClientService teamRestClientService;

    @GetMapping
    public String getAllPlayers(Model model) {
        List<PlayerDto> players = playerRestClientService.getAllPlayers();
        model.addAttribute("players", players);

        return "player/index";
    }

//    @GetMapping("/{uuid}")
//    public String findByUuid(Model model, @PathVariable("uuid") @NonNull final String uuid) {
//        PlayerDto player = playerRestClientService.getPlayerByUuid(uuid);
//        model.addAttribute("player", player);
//
//        return "player/details";
//    }

    @GetMapping("/add/{teamUuid}")
    public String showCreatePage(@PathVariable("teamUuid") @NonNull final String teamUuid, Model model) {
        model.addAttribute("teamUuid", teamUuid);

        return "player/create";
    }

    @PostMapping("/add/{teamUuid}")
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
    public String showEditPage(Model model, @PathVariable("uuid") @NonNull final String uuid) {
        PlayerDto player = playerRestClientService.getPlayerByUuid(uuid);
        model.addAttribute("player", player);

        return "player/edit";
    }

    @PostMapping("/edit/{uuid}")
    public String editTeam(@PathVariable("uuid") String uuid, @ModelAttribute PlayerUpdateDto playerUpdateDto,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("player", playerUpdateDto);
            return "player/edit";
        }

        final var playerDto = playerRestClientService.getPlayerByUuid(uuid);

        playerRestClientService.updatePlayer(uuid, playerUpdateDto);

        return "redirect:/teams/" + playerDto.getTeam().getUuid();
    }

    @PostMapping("/delete/{uuid}")
    public String delete(@PathVariable("uuid") @NonNull final String uuid) {
        final var playerDto = playerRestClientService.getPlayerByUuid(uuid);

        playerRestClientService.deletePlayer(uuid);

        return "redirect:/teams/" + playerDto.getTeam().getUuid();
    }
}
