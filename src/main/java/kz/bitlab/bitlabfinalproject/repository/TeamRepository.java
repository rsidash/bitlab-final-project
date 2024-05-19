package kz.bitlab.bitlabfinalproject.repository;

import kz.bitlab.bitlabfinalproject.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByUserId(Long ownerId);
    Optional<Team> findByName(String name);
    Optional<Team> findByUuid(String uuid);
}
