package kz.bitlab.bitlabfinalproject.external.client;

import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffDto;
import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffUpdateDto;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;
import kz.bitlab.bitlabfinalproject.enums.PlayingPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StaffRestClient {
    private final RestTemplate restTemplate;

    @Value("${api.url}")
    private String url;

    public List<StaffDto> getAllStaff(final String team, final JobTitle jobTitle) {
        final var staffList = restTemplate.getForObject(
                url + "/staff?team={team}&jobTitle={jobTitle}",
                StaffDto[].class, team, jobTitle
        );

        if (Objects.nonNull(staffList)) {
            return Arrays.asList(staffList);
        } else {
            return Collections.emptyList();
        }
    }

    public List<StaffDto> getAllTeamStaff(@NonNull final String teamUuid) {
        final var staffList = restTemplate.getForObject(
                url + "/staff/by-team/{teamUuid}",
                StaffDto[].class, teamUuid
        );

        if (Objects.nonNull(staffList)) {
            return Arrays.asList(staffList);
        } else {
            return Collections.emptyList();
        }
    }

    public List<StaffDto> getAllTeamStaffByJobTitle(@NonNull final String teamUuid, @NonNull final JobTitle jobTitle) {
        final var staffList = restTemplate.getForObject(
                url + "/staff/by-team/{teamUuid}/by-job?jobTitle={jobTitle}",
                StaffDto[].class, teamUuid, jobTitle
        );

        if (Objects.nonNull(staffList)) {
            return Arrays.asList(staffList);
        } else {
            return Collections.emptyList();
        }
    }

    @Nullable
    public StaffDto getStaffByUuid(@NonNull final String uuid) {
        return restTemplate.getForObject(
                url + "/staff/{uuid}",
                StaffDto.class, uuid
        );
    }

    public void createStaff(@NonNull final StaffDto staffDto, @NonNull final String teamUuid) {
        restTemplate.postForObject(url + "/staff/add/{uuid}", staffDto, StaffDto.class, teamUuid);
    }

    public void updateStaff(@NonNull final String uuid, @NonNull final StaffUpdateDto staffUpdateDto) {
        restTemplate.postForObject(url + "/staff/update/{uuid}", staffUpdateDto, StaffDto.class, uuid);
    }

    public void deleteStaff(@NonNull final String uuid) {
        restTemplate.delete(url + "/staff/{uuid}", uuid);
    }
}
