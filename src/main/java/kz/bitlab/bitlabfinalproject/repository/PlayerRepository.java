package kz.bitlab.bitlabfinalproject.repository;

import kz.bitlab.bitlabfinalproject.entity.Player;
import kz.bitlab.bitlabfinalproject.enums.PlayingPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamNameOrderByLastName(String teamName);
    List<Player> findByPlayingPositionOrderByLastName(PlayingPosition playingPosition);
    List<Player> findByTeamNameAndPlayingPositionOrderByLastName(String teamName, PlayingPosition playingPosition);
    Optional<Player> findByUuid(String uuid);
}
