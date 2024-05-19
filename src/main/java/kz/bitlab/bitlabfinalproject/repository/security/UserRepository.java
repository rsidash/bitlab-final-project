package kz.bitlab.bitlabfinalproject.repository.security;

import jakarta.transaction.Transactional;
import kz.bitlab.bitlabfinalproject.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
