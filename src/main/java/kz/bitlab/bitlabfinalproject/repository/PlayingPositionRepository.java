package kz.bitlab.bitlabfinalproject.repository;

import kz.bitlab.bitlabfinalproject.entity.PlayingPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayingPositionRepository extends JpaRepository<PlayingPosition, Long> {
}
