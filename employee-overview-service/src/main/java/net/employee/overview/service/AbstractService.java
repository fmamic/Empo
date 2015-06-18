package net.employee.overview.service;

import com.google.common.collect.ImmutableMap;
import java.util.List;

import net.employee.overview.dao.RoleRepository;
import net.employee.overview.model.AbstractCode;
import net.employee.overview.model.code.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<T extends AbstractCode> implements Service {

    private final RoleRepository m_roleRepository;

    private final ImmutableMap<Class<Role>, RoleRepository> m_repositories;

    @Autowired
    public AbstractService(final RoleRepository p_roleRepository) {
        m_roleRepository = p_roleRepository;

        m_repositories = ImmutableMap.of(Role.class, m_roleRepository);
    }

    protected final JpaRepository getRepository(final Class p_repository) {
        return m_repositories.get(p_repository);
    }

    @Override
    public final List findAll(final Class p_type) {
        return getRepository(p_type).findAll();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final T getOne(final Class p_type, final Long p_id) {
        return (T) getRepository(p_type).findOne(p_id);
    }
}
