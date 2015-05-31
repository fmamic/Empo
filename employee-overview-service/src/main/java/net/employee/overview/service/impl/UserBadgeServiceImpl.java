package net.employee.overview.service.impl;

import net.employee.overview.dao.*;
import net.employee.overview.service.AbstractJoinPersistableService;
import net.employee.overview.service.UserBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBadgeServiceImpl extends AbstractJoinPersistableService implements UserBadgeService  {

    @Autowired
    protected UserBadgeServiceImpl(
        UserBadgeRepository userBadgeRepository,
        UserTagRepository userTagRepository,
        UserProjectRepository userProjectRepository,
        BadgeTagRepository badgeTagRepository,
        ProjectTagRepository projectTagRepository) {
        super(userBadgeRepository, userTagRepository, userProjectRepository, badgeTagRepository,
            projectTagRepository);
    }

}
