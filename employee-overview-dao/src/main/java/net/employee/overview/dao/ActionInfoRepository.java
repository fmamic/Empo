package net.employee.overview.dao;

import net.employee.overview.model.entity.ActionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionInfoRepository extends JpaRepository<ActionInfo, Long> {
}
