package cz.sharee.backend.repositories;

import cz.sharee.backend.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
