package net.employee.overview.service.impl;

import net.employee.overview.dao.BadgeTagRepository;
import net.employee.overview.dao.ProjectTagRepository;
import net.employee.overview.dao.UserBadgeRepository;
import net.employee.overview.dao.UserProjectRepository;
import net.employee.overview.dao.UserTagRepository;
import net.employee.overview.service.AbstractJoinPersistableService;
import net.employee.overview.service.BadgeTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeTagServiceImpl extends AbstractJoinPersistableService implements BadgeTagService {

    @Autowired
    protected BadgeTagServiceImpl(
        final UserBadgeRepository p_userBadgeRepository,
            final UserTagRepository p_userTagRepository,
            final UserProjectRepository p_userProjectRepository,
            final BadgeTagRepository p_badgeTagRepository,
            final ProjectTagRepository p_projectTagRepository) {

        super(p_userBadgeRepository, p_userTagRepository, p_userProjectRepository, p_badgeTagRepository,
                p_projectTagRepository);
    }
}
