package kz.bitlab.bitlabfinalproject.repository;

import kz.bitlab.bitlabfinalproject.entity.Staff;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByTeamNameOrderByLastName(String teamName);
    List<Staff> findByJobTitleOrderByLastName(JobTitle jobTitle);
    List<Staff> findByTeamNameAndJobTitleOrderByLastName(String teamName, JobTitle jobTitle);
    Optional<Staff> findByUuid(String uuid);
    List<Staff> findAllByTeamUuidAndJobTitle(String teamUuid, JobTitle jobTitle);
}
