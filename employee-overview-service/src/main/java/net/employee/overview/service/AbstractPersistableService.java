package net.employee.overview.service;

import com.google.common.collect.ImmutableMap;
import java.util.List;

import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.model.AbstractPersistable;
import net.employee.overview.model.entity.Badge;
import net.employee.overview.model.entity.Project;
import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractPersistableService<T extends AbstractPersistable> implements PersistableService {

    private final BadgeRepository   m_badgeRepository;
    private final ProjectRepository m_projectRepository;
    private final TagRepository     m_tagRepository;
    private final UserRepository    m_userRepository;

    private ImmutableMap<Class<? extends AbstractPersistable>, JpaRepository<? extends AbstractPersistable, Long>>
            m_repositories;

    @Autowired
    public AbstractPersistableService(final BadgeRepository p_badgeRepository,
            final ProjectRepository p_projectRepository, final TagRepository p_tagRepository,
            final UserRepository p_userRepository) {

        m_badgeRepository = p_badgeRepository;
        m_projectRepository = p_projectRepository;
        m_tagRepository = p_tagRepository;
        m_userRepository = p_userRepository;

        m_repositories =
                ImmutableMap.of(Badge.class, m_badgeRepository, Project.class, m_projectRepository,
                        Tag.class, m_tagRepository, User.class, m_userRepository);
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
