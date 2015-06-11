package net.employee.overview.service;

import java.util.List;

import net.employee.overview.model.entity.Badge;
import net.employee.overview.model.entity.BadgeType;

public interface BadgeService extends PersistableService {

    List<BadgeType> fetchAllBadgeType();

    BadgeType fetchBadgeType(Long p_id);

    List<Badge> fetchAllBadges();

    List<Badge> fetchBadgesByIds(List<Long> p_ids);
}
