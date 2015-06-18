package net.employee.overview.service;

import com.google.common.collect.ImmutableMap;

import java.util.List;

import net.employee.overview.model.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractPersistableService<T extends AbstractPersistable> implements PersistableService {

    private static final String UNCHECKED = "unchecked";

    private ImmutableMap<Class<? extends AbstractPersistable>, JpaRepository<? extends AbstractPersistable, Long>>
            m_repositories;

    protected final JpaRepository getRepository(final Class<T> p_repository) {
        return m_repositories.get(p_repository);
    }

    @SuppressWarnings(UNCHECKED)
    @Override
    public final List findAll(final Class p_type) {
        return getRepository(p_type).findAll();
    }

    @SuppressWarnings(UNCHECKED)
    @Override
    public final T getOne(final Class p_type, final Long p_id) {
        return (T) getRepository(p_type).findOne(p_id);
    }

    @SuppressWarnings(UNCHECKED)
    @Override
    public final AbstractPersistable save(final Class p_type, final AbstractPersistable p_object) {
        return (T) getRepository(p_type).save(p_object);
    }

    @SuppressWarnings(UNCHECKED)
    @Override
    public final void delete(final Class p_type, final AbstractPersistable p_object) {
        getRepository(p_type).delete(p_object);
    }

    public final void setRepositories(final ImmutableMap<Class<? extends AbstractPersistable>, JpaRepository<? extends AbstractPersistable, Long>> p_repositories) {
        m_repositories = p_repositories;
    }

    public final ImmutableMap<Class<? extends AbstractPersistable>, JpaRepository<? extends AbstractPersistable, Long>> getRepositories() {
        return m_repositories;
    }
}
