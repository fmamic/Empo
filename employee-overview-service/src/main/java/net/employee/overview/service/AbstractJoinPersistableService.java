package net.employee.overview.service;

import com.google.common.collect.ImmutableMap;

import net.employee.overview.dao.BadgeTagRepository;
import net.employee.overview.dao.ProjectTagRepository;
import net.employee.overview.dao.UserBadgeRepository;
import net.employee.overview.dao.UserProjectRepository;
import net.employee.overview.dao.UserTagRepository;
import net.employee.overview.model.AbstractPersistable;
import net.employee.overview.model.entity.BadgeTag;
import net.employee.overview.model.entity.ProjectTag;
import net.employee.overview.model.entity.UserBadge;
import net.employee.overview.model.entity.UserProject;
import net.employee.overview.model.entity.UserTag;

public abstract class AbstractJoinPersistableService<T extends AbstractPersistable> extends AbstractPersistableService {

    private final UserBadgeRepository   m_userBadgeRepository;
    private final UserTagRepository     m_userTagRepository;
    private final UserProjectRepository m_userProjectRepository;
    private final BadgeTagRepository    m_badgeTagRepository;
    private final ProjectTagRepository  m_projectTagRepository;

    protected AbstractJoinPersistableService(final UserBadgeRepository p_userBadgeRepository,
            final UserTagRepository p_userTagRepository, final UserProjectRepository p_userProjectRepository,
            final BadgeTagRepository p_badgeTagRepository, final ProjectTagRepository p_projectTagRepository) {
        m_userBadgeRepository = p_userBadgeRepository;
        m_userTagRepository = p_userTagRepository;
        m_userProjectRepository = p_userProjectRepository;
        m_badgeTagRepository = p_badgeTagRepository;
        m_projectTagRepository = p_projectTagRepository;

        setRepositories(
                ImmutableMap.of(UserBadge.class, m_userBadgeRepository, UserTag.class, m_userTagRepository,
                        UserProject.class, m_userProjectRepository, BadgeTag.class, m_badgeTagRepository, ProjectTag.class, m_projectTagRepository));
    }

}
