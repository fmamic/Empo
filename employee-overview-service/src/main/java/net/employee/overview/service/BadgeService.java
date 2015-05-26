package net.employee.overview.service;

import net.employee.overview.model.entity.Badge;
import net.employee.overview.model.entity.BadgeType;

import java.util.List;

public interface BadgeService extends PersistableService {

    List<BadgeType> fetchAllBadgeType();

    BadgeType fetchBadgeType(Long id);

    List<Badge> fetchAllBadges();

    List<Badge> fetchBadgesByIds(List<Long> ids);
}
