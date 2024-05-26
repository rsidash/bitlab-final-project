package kz.bitlab.bitlabfinalproject.service.impl;

import io.micrometer.common.util.StringUtils;
import kz.bitlab.bitlabfinalproject.entity.Staff;
import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffDto;
import kz.bitlab.bitlabfinalproject.entity.dto.staff.StaffUpdateDto;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;
import kz.bitlab.bitlabfinalproject.exception.NotFoundException;
import kz.bitlab.bitlabfinalproject.repository.StaffRepository;
import kz.bitlab.bitlabfinalproject.service.StaffService;
import kz.bitlab.bitlabfinalproject.service.TeamService;
import kz.bitlab.bitlabfinalproject.service.mapper.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final TeamService teamService;

    @Transactional(readOnly = true)
    @Override
    public List<StaffDto> findAll() {
        final var staffList = staffRepository.findAll();

        return staffMapper.toDtoList(staffList);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StaffDto> findByTeamName(@NonNull final String teamUuid) {
        final var staffList = staffRepository.findByTeamNameOrderByLastName(teamUuid);

        return staffMapper.toDtoList(staffList);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StaffDto> findByTeamNameAndJobTitle(final String teamName, final JobTitle jobTitle) {
        List<Staff> staffList;

        if (StringUtils.isEmpty(teamName) && Objects.nonNull(jobTitle)) {
            staffList = staffRepository.findByJobTitleOrderByLastName(jobTitle);
        } else if (StringUtils.isEmpty(teamName) && Objects.isNull(jobTitle)) {
            staffList = staffRepository.findAll();
        } else {
            if (Objects.nonNull(jobTitle)) {
                staffList = staffRepository.findByTeamNameAndJobTitleOrderByLastName(teamName, jobTitle);
            } else {
                staffList = staffRepository.findByTeamNameOrderByLastName(teamName);
            }
        }

        return staffMapper.toDtoList(staffList);
    }

    @Transactional(readOnly = true)
    @Override
    public StaffDto findByUuid(@NonNull final String uuid) {
        final var staff = staffRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Staff not found"));

        return staffMapper.toDto(staff);
    }

    @Transactional
    @Override
    public void create(@NonNull final StaffDto staffDto, @NonNull final String teamUuid) {
        final var team = teamService.findEntityByUuid(teamUuid);

        final var staff = staffMapper.toEntity(staffDto);
        staff.setTeam(team);

        staffRepository.save(staff);
    }

    @Transactional
    @Override
    public void update(@NonNull final String uuid, @NonNull final StaffUpdateDto staffDto) {
        final var staff = staffRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Staff not found"));

        staffMapper.updateFromDto(staffDto, staff);
        staffRepository.save(staff);
    }

    @Transactional
    @Override
    public void delete(@NonNull final String uuid) {
        staffRepository.findByUuid(uuid).ifPresentOrElse(
                staffRepository::delete,
                () -> {
                    throw new NotFoundException("Staff not found");
                }
        );
    }
}
