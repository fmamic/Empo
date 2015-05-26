package net.employee.overview.service;

import com.google.common.collect.ImmutableMap;
import net.employee.overview.dao.RoleRepository;
import net.employee.overview.model.AbstractCode;
import net.employee.overview.model.code.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public class AbstractService<T extends AbstractCode> implements Service {

    private final RoleRepository roleRepository;
    private ImmutableMap<Class<Role>, RoleRepository> repositories;

    @Autowired
    public AbstractService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

        repositories = ImmutableMap.of(Role.class, roleRepository);
    }

    protected JpaRepository getRepository(final Class repository) {
        return repositories.get(repository);
    }

    @Override
    public List findAll(final Class type) {
        return getRepository(type).findAll();
    }

    @Override
    public T getOne(final Class type, final Long id) {
        return (T) getRepository(type).findOne(id);
    }
}
