package kz.bitlab.bitlabfinalproject.service.impl;

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
    public List<StaffDto> findByTeamUuid(@NonNull final String teamUuid) {
        final var staffList = staffRepository.findByTeamUuid(teamUuid);

        return staffMapper.toDtoList(staffList);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StaffDto> findByTeamUuidAndJobTitle(@NonNull final String teamUuid, @NonNull final JobTitle jobTitle) {
        final var staffList = staffRepository.findAllByTeamUuidAndJobTitle(teamUuid, jobTitle);

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
