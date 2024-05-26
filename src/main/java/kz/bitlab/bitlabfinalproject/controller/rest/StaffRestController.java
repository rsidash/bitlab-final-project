package kz.bitlab.bitlabfinalproject.controller.rest;

import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffDto;
import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffUpdateDto;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;
import kz.bitlab.bitlabfinalproject.enums.PlayingPosition;
import kz.bitlab.bitlabfinalproject.exception.NotFoundException;
import kz.bitlab.bitlabfinalproject.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StaffRestController {
    private final StaffService staffService;

    @GetMapping("/staff")
    public List<StaffDto> findAll(@RequestParam(value = "team", required = false) final String team,
                                  @RequestParam(value = "jobTitle", required = false) final JobTitle jobTitle) {
        return staffService.findByTeamNameAndJobTitle(team, jobTitle);
    }

    @GetMapping("/staff/by-team/{teamUuid}")
    public List<StaffDto> findByTeamUuid(@PathVariable("teamUuid") @NonNull final String teamUuid) {
        return staffService.findByTeamName(teamUuid);
    }

//    @GetMapping("/staff/by-team/{teamUuid}/by-job")
//    public List<StaffDto> findByTeamUuidAndJobTitle(@PathVariable("teamUuid") @NonNull final String teamUuid,
//                                                    @RequestParam("jobTitle") @NonNull final JobTitle jobTitle) {
//        return staffService.findByTeamNameAndJobTitle(teamUuid, jobTitle);
//    }

    @GetMapping("/staff/{uuid}")
    public StaffDto findByUuid(@PathVariable("uuid") @NonNull final String uuid) {
        try {
            return staffService.findByUuid(uuid);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/staff/add/{teamUuid}")
    public void create(@PathVariable("teamUuid") @NonNull final String teamUuid,
                       @RequestBody @NonNull final StaffDto staffDto) {
        staffService.create(staffDto, teamUuid);
    }

    @PostMapping("/staff/update/{uuid}")
    public void update(@PathVariable("uuid") @NonNull final String uuid,
                       @RequestBody @NonNull final StaffUpdateDto staffUpdateDto) {
        try {
            staffService.update(uuid, staffUpdateDto);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/staff/{uuid}")
    public void delete(@PathVariable("uuid") @NonNull String uuid) {
        try {
            staffService.delete(uuid);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
