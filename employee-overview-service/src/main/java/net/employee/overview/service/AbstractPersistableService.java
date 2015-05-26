package net.employee.overview.service;

import com.google.common.collect.ImmutableMap;
import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.model.Persistable;
import net.employee.overview.model.entity.Badge;
import net.employee.overview.model.entity.Project;
import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SuppressWarnings("unchecked")
public class AbstractPersistableService<T extends Persistable> implements PersistableService {

    private final BadgeRepository                                                                        badgeRepository;
    private final ProjectRepository                                                                      projectRepository;
    private final TagRepository                                                                          tagRepository;
    private final UserRepository                                                                         userRepository;
    private       ImmutableMap<Class<? extends Persistable>, JpaRepository<? extends Persistable, Long>> repositories;

    @Autowired
    public AbstractPersistableService(final BadgeRepository badgeRepository,
            final ProjectRepository projectRepository, final TagRepository tagRepository,
            final UserRepository userRepository) {

        this.badgeRepository = badgeRepository;
        this.projectRepository = projectRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;

        repositories = ImmutableMap.of(Badge.class, badgeRepository, Project.class, projectRepository,
                Tag.class, tagRepository, User.class, userRepository);
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
