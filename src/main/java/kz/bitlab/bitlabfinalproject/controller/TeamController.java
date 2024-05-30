package kz.bitlab.bitlabfinalproject.controller;

import jakarta.validation.Valid;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDataDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamUpdateDto;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;
import kz.bitlab.bitlabfinalproject.exception.NotAllowedException;
import kz.bitlab.bitlabfinalproject.external.service.StaffRestClientService;
import kz.bitlab.bitlabfinalproject.external.service.TeamRestClientService;
import kz.bitlab.bitlabfinalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    private final TeamRestClientService teamRestClientService;
    private final StaffRestClientService staffRestClientService;
    private final UserService userService;

    @GetMapping
    public String getAllTeams(Model model) {
        final var teams = teamRestClientService.getAllTeams();
        model.addAttribute("teams", teams);

        return "team/index";
    }

    @GetMapping("/{uuid}")
    public String findByUuid(Model model, @PathVariable("uuid") @NonNull final String uuid) {
        final var team = teamRestClientService.getTeamByUuid(uuid);
        model.addAttribute("team", team);

        final var coaches = staffRestClientService.getAllTeamStaffByJobTitle(team.getName(), JobTitle.COACH);
        final var managers = staffRestClientService.getAllTeamStaffByJobTitle(team.getName(), JobTitle.MANAGER);

        model.addAttribute("coaches", coaches);
        model.addAttribute("managers", managers);

        final var currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);

        return "team/details";
    }

    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String showCreatePage() {
        return "team/create";
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String create(@ModelAttribute @Valid TeamDto teamDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "team/create";
        }

        try {
            teamRestClientService.createTeam(teamDto);
        } catch (NotAllowedException exception) {
            model.addAttribute("errors", exception.getMessage());
            return "team/create";
        }
        return "redirect:/teams";
    }

    @GetMapping("/edit/{uuid}")
    @PreAuthorize("isAuthenticated()")
    public String showEditPage(Model model, @PathVariable("uuid") @NonNull final String uuid) {
        TeamDataDto team = teamRestClientService.getTeamByUuid(uuid);
        model.addAttribute("team", team);

        return "team/edit";
    }

    @PostMapping("/edit/{uuid}")
    @PreAuthorize("isAuthenticated()")
    public String editTeam(@PathVariable("uuid") String uuid, @ModelAttribute TeamUpdateDto teamDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("team", teamDto);
            return "team/edit";
        }

        try {
            teamRestClientService.updateTeam(uuid, teamDto);
        } catch (NotAllowedException exception) {
            model.addAttribute("errors", exception.getMessage());
            model.addAttribute("team", teamDto);
            return "team/edit";
        }

        return "redirect:/teams/" + uuid;
    }

    @PostMapping("/delete/{uuid}")
    public String delete(@PathVariable("uuid") @NonNull final String uuid) {
        try {
            teamRestClientService.deleteTeam(uuid);
        } catch (NotAllowedException ignored) {}

        return "redirect:/teams";
    }
}
