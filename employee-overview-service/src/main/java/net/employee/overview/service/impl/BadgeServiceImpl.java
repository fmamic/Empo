package net.employee.overview.service.impl;

import java.util.List;

import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.BadgeTypeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.model.entity.Badge;
import net.employee.overview.model.entity.BadgeType;
import net.employee.overview.service.AbstractPersistableService;
import net.employee.overview.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeServiceImpl extends AbstractPersistableService implements BadgeService {

    private final BadgeTypeRepository m_badgeTypeRepository;

    @Autowired
    public BadgeServiceImpl(final BadgeRepository p_badgeRepository,
            final ProjectRepository p_projectRepository,
            final TagRepository p_tagRepository,
            final UserRepository p_userRepository, final BadgeTypeRepository p_badgeTypeRepository) {
        super(p_badgeRepository, p_projectRepository, p_tagRepository, p_userRepository);
        m_badgeTypeRepository = p_badgeTypeRepository;
    }

    public final List<BadgeType> fetchAllBadgeType() {
        return m_badgeTypeRepository.findAll();
    }

    public final BadgeType fetchBadgeType(final Long p_id) {
        return m_badgeTypeRepository.findOne(p_id);
    }

    @SuppressWarnings("unchecked")
    public final List<Badge> fetchAllBadges() {
        return getRepository(Badge.class).findAll();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final List<Badge> fetchBadgesByIds(final List<Long> p_ids) {
        return ((BadgeRepository) getRepository(Badge.class)).fetchBadgesByIds(p_ids);
    }
}
