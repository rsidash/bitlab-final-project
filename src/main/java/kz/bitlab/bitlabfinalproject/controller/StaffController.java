package kz.bitlab.bitlabfinalproject.controller;

import jakarta.validation.Valid;
import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffDto;
import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffUpdateDto;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;
import kz.bitlab.bitlabfinalproject.external.service.StaffRestClientService;
import kz.bitlab.bitlabfinalproject.external.service.TeamRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {
    private final StaffRestClientService staffRestClientService;
    private final TeamRestClientService teamRestClientService;

    @GetMapping
    public String getAllPlayers(@RequestParam(value = "team", required = false) final String team,
                                @RequestParam(value = "jobTitle", required = false) final JobTitle jobTitle,
                                Model model) {
        List<StaffDto> staffList = staffRestClientService.getAllStaff(team, jobTitle);
        model.addAttribute("staffList", staffList);

        final var teamNames = teamRestClientService.getAllTeams().stream()
                .map(TeamDto::getName).toList();
        model.addAttribute("teamNames", teamNames);

        return "staff/index";
    }

    @GetMapping("/add/{teamUuid}")
    public String showCreatePage(@PathVariable("teamUuid") @NonNull final String teamUuid, Model model) {
        model.addAttribute("teamUuid", teamUuid);

        return "staff/create";
    }

    @PostMapping("/add/{teamUuid}")
    public String create(@ModelAttribute @Valid StaffDto staffDto, BindingResult result,
                         Model model, @PathVariable("teamUuid") @NonNull final String teamUuid) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "staff/create";
        }

        staffRestClientService.createStaff(staffDto, teamUuid);
        return "redirect:/teams/" + teamUuid;
    }

    @GetMapping("/edit/{uuid}")
    public String showEditPage(Model model, @PathVariable("uuid") @NonNull final String uuid) {
        StaffDto staff = staffRestClientService.getStaffByUuid(uuid);
        model.addAttribute("staff", staff);

        return "staff/edit";
    }

    @PostMapping("/edit/{uuid}")
    public String edit(@PathVariable("uuid") String uuid, @ModelAttribute StaffUpdateDto staffUpdateDto,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("staff", staffUpdateDto);
            return "staff/edit";
        }

        final var staffDto = staffRestClientService.getStaffByUuid(uuid);

        staffRestClientService.updateStaff(uuid, staffUpdateDto);

        return Objects.nonNull(staffDto) && Objects.nonNull(staffDto.getTeam()) ?
                "redirect:/teams/" + staffDto.getTeam().getUuid() : "redirect:/teams";
    }

    @PostMapping("/delete/{uuid}")
    public String delete(@PathVariable("uuid") @NonNull final String uuid) {
        final var staffDto = staffRestClientService.getStaffByUuid(uuid);

        staffRestClientService.deleteStaff(uuid);

        return Objects.nonNull(staffDto) && Objects.nonNull(staffDto.getTeam()) ?
                "redirect:/teams/" + staffDto.getTeam().getUuid() : "redirect:/teams";
    }
}
