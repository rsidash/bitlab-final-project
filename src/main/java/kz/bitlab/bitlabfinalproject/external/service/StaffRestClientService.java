package kz.bitlab.bitlabfinalproject.external.service;

import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffDto;
import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffUpdateDto;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;
import kz.bitlab.bitlabfinalproject.exception.NotAllowedException;
import kz.bitlab.bitlabfinalproject.external.client.StaffRestClient;
import kz.bitlab.bitlabfinalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffRestClientService {
    private final StaffRestClient staffRestClient;
    private final UserService userService;

    public List<StaffDto> getAllStaff(final String team, final JobTitle jobTitle) {
        try {
            return staffRestClient.getAllStaff(team, jobTitle);
        } catch (HttpClientErrorException e) {
            log.error("Staff not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return Collections.emptyList();
    }

    public List<StaffDto> getAllTeamStaff(@NonNull final String teamUuid) {
        try {
            return staffRestClient.getAllTeamStaff(teamUuid);
        } catch (HttpClientErrorException e) {
            log.error("Staff not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return Collections.emptyList();
    }

    public List<StaffDto> getAllTeamStaffByJobTitle(@NonNull final String teamName, @NonNull final JobTitle jobTitle) {
        try {
            return staffRestClient.getAllTeamStaffByJobTitle(teamName, jobTitle);
        } catch (HttpClientErrorException e) {
            log.error("Staff not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return Collections.emptyList();
    }

    @Nullable
    public StaffDto getStaffByUuid(@NonNull final String uuid) {
        try {
            return staffRestClient.getStaffByUuid(uuid);
        } catch (HttpClientErrorException e) {
            log.error("Staff not found", e);
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }

        return null;
    }

    public void createStaff(@NonNull final StaffDto staffDto, @NonNull final String teamUuid) {
        final var user = userService.getCurrentUser();

        if (Objects.isNull(user)) {
            throw new NotAllowedException();
        }

        try {
            staffRestClient.createStaff(staffDto, teamUuid);
        } catch (HttpClientErrorException e) {
            log.error(String.valueOf(e));
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }
    }

    public void updateStaff(@NonNull final String uuid, @NonNull final StaffUpdateDto staffUpdateDto) {
        final var user = userService.getCurrentUser();

        if (Objects.isNull(user)) {
            throw new NotAllowedException();
        }

        try {
            staffRestClient.updateStaff(uuid, staffUpdateDto);
        } catch (HttpClientErrorException e) {
            log.error(String.valueOf(e));
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }
    }

    public void deleteStaff(@NonNull final String uuid) {
        final var user = userService.getCurrentUser();

        if (Objects.isNull(user)) {
            throw new NotAllowedException();
        }

        try {
            staffRestClient.deleteStaff(uuid);
        } catch (HttpClientErrorException e) {
            log.error(String.valueOf(e));
        } catch (Exception e) {
            log.error("Service unavailable", e);
        }
    }
}
