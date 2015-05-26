package net.employee.overview.service.impl;

import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.BadgeTypeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.model.entity.Badge;
import net.employee.overview.model.entity.BadgeType;
import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.TagType;
import net.employee.overview.service.AbstractPersistableService;
import net.employee.overview.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeServiceImpl extends AbstractPersistableService implements BadgeService {

    private final BadgeTypeRepository badgeTypeRepository;

    @Autowired
    public BadgeServiceImpl(final BadgeRepository badgeRepository,
            final ProjectRepository projectRepository,
            final TagRepository tagRepository,
            final UserRepository userRepository, final BadgeTypeRepository p_badgeTypeRepository) {
        super(badgeRepository, projectRepository, tagRepository, userRepository);
        badgeTypeRepository = p_badgeTypeRepository;
    }

    public List<BadgeType> fetchAllBadgeType() {
        return badgeTypeRepository.findAll();
    }

    public BadgeType fetchBadgeType(final Long id) {
        return badgeTypeRepository.findOne(id);
    }

    @SuppressWarnings("unchecked")
    public List<Badge> fetchAllBadges() {
        return getRepository(Badge.class).findAll();
    }

    @Override public List<Badge> fetchBadgesByIds(final List<Long> ids) {
        return ((BadgeRepository)getRepository(Badge.class)).fetchBadgesByIds(ids);
    }
}
