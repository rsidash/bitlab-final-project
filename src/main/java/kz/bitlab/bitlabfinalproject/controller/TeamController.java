package kz.bitlab.bitlabfinalproject.controller;

import jakarta.validation.Valid;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;
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
@RequestMapping("/teams")
public class TeamController {
    private final TeamRestClientService teamRestClientService;

    @GetMapping
    public String getAllTeams(Model model) {
        List<TeamDto> teams = teamRestClientService.getAllTeams();
        model.addAttribute("teams", teams);

        return "team/index";
    }

    @GetMapping("/{uuid}")
    public String findByUuid(Model model, @PathVariable("uuid") @NonNull final String uuid) {
        TeamDataDto team = teamRestClientService.getTeamByUuid(uuid);
        model.addAttribute("team", team);

        return "team/details";
    }

    @GetMapping("/create")
    public String showCreatePage() {
        return "team/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid TeamDto teamDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "team/create";
        }

        teamRestClientService.createTeam(teamDto);
        return "redirect:/teams";
    }

    @GetMapping("/edit/{uuid}")
    public String showEditPage(Model model, @PathVariable("uuid") @NonNull final String uuid) {
        TeamDataDto team = teamRestClientService.getTeamByUuid(uuid);
        model.addAttribute("team", team);

        return "team/edit";
    }

    @PostMapping("/edit/{uuid}")
    public String editTeam(@PathVariable("uuid") String uuid, @ModelAttribute TeamUpdateDto teamDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("team", teamDto);
            return "team/edit";
        }

        teamRestClientService.updateTeam(uuid, teamDto);

        return "redirect:/teams";
    }

    @PostMapping("/delete/{uuid}")
    public String delete(@PathVariable("uuid") @NonNull final String uuid) {
        teamRestClientService.deleteTeam(uuid);

        return "redirect:/teams";
    }
}
