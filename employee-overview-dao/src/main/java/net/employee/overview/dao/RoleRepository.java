package net.employee.overview.dao;

import net.employee.overview.model.code.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
