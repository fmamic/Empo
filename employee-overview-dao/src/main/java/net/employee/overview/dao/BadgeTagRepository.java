package net.employee.overview.dao;

import net.employee.overview.model.entity.BadgeTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeTagRepository extends JpaRepository<BadgeTag, Long> {
}
