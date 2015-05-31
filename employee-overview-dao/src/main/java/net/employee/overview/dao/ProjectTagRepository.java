package net.employee.overview.dao;

import net.employee.overview.model.entity.ProjectTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTagRepository extends JpaRepository<ProjectTag, Long> {
}
