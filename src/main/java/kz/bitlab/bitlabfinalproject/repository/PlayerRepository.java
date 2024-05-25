package kz.bitlab.bitlabfinalproject.repository;

import kz.bitlab.bitlabfinalproject.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamNameOrderByLastName(String teamName);
    Optional<Player> findByUuid(String uuid);
}
