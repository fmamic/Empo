package net.employee.overview.service;

import com.google.common.collect.ImmutableMap;
import net.employee.overview.dao.*;
import net.employee.overview.model.Persistable;
import net.employee.overview.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractJoinPersistableService<T extends Persistable> implements PersistableService {

    private final UserBadgeRepository userBadgeRepository;
    private final UserTagRepository userTagRepository;
    private final UserProjectRepository userProjectRepository;
    private final BadgeTagRepository badgeTagRepository;
    private final ProjectTagRepository projectTagRepository;

    private ImmutableMap<Class<? extends Persistable>, JpaRepository<? extends Persistable, Long>>
        repositories;

    protected AbstractJoinPersistableService(UserBadgeRepository userBadgeRepository,
        UserTagRepository userTagRepository, UserProjectRepository userProjectRepository,
        BadgeTagRepository badgeTagRepository, ProjectTagRepository projectTagRepository) {
        this.userBadgeRepository = userBadgeRepository;
        this.userTagRepository = userTagRepository;
        this.userProjectRepository = userProjectRepository;
        this.badgeTagRepository = badgeTagRepository;
        this.projectTagRepository = projectTagRepository;

        repositories =
            ImmutableMap.of(UserBadge.class, userBadgeRepository, UserTag.class, userTagRepository,
                UserProject.class, userProjectRepository, BadgeTag.class, badgeTagRepository, ProjectTag.class, projectTagRepository);
    }

    protected JpaRepository getRepository(final Class<T> repository) {
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

    @Override public Persistable save(final Class type, final Persistable object) {
        return (T) getRepository(type).save(object);
    }

    @Override public void delete(final Class type, final Persistable object) {
        getRepository(type).delete(object);
    }
}
