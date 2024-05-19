package kz.bitlab.bitlabfinalproject.repository.security;

import jakarta.transaction.Transactional;
import kz.bitlab.bitlabfinalproject.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
