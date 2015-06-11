package net.employee.overview.service;

import com.google.common.collect.ImmutableMap;
import java.util.List;

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
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractJoinPersistableService<T extends AbstractPersistable> implements PersistableService {

    private final UserBadgeRepository   m_userBadgeRepository;
    private final UserTagRepository     m_userTagRepository;
    private final UserProjectRepository m_userProjectRepository;
    private final BadgeTagRepository    m_badgeTagRepository;
    private final ProjectTagRepository  m_projectTagRepository;

    private ImmutableMap<Class<? extends AbstractPersistable>, JpaRepository<? extends AbstractPersistable, Long>>
            m_repositories;

    protected AbstractJoinPersistableService(final UserBadgeRepository p_userBadgeRepository,
            final UserTagRepository p_userTagRepository, final UserProjectRepository p_userProjectRepository,
            final BadgeTagRepository p_badgeTagRepository, final ProjectTagRepository p_projectTagRepository) {
        m_userBadgeRepository = p_userBadgeRepository;
        m_userTagRepository = p_userTagRepository;
        m_userProjectRepository = p_userProjectRepository;
        m_badgeTagRepository = p_badgeTagRepository;
        m_projectTagRepository = p_projectTagRepository;

        m_repositories =
                ImmutableMap.of(UserBadge.class, m_userBadgeRepository, UserTag.class, m_userTagRepository,
                        UserProject.class, m_userProjectRepository, BadgeTag.class, m_badgeTagRepository, ProjectTag.class, m_projectTagRepository);
    }

    protected final JpaRepository getRepository(final Class<T> p_repository) {
        return m_repositories.get(p_repository);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final List findAll(final Class p_type) {
        return getRepository(p_type).findAll();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final T getOne(final Class p_type, final Long p_id) {
        return (T) getRepository(p_type).findOne(p_id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final AbstractPersistable save(final Class p_type, final AbstractPersistable p_object) {
        return (T) getRepository(p_type).save(p_object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void delete(final Class p_type, final AbstractPersistable p_object) {
        getRepository(p_type).delete(p_object);
    }
}
