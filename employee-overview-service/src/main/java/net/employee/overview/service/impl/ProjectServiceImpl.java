package net.employee.overview.service.impl;

import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.service.AbstractPersistableService;
import net.employee.overview.service.ProjectService;

public class ProjectServiceImpl extends AbstractPersistableService implements ProjectService {

    public ProjectServiceImpl(final BadgeRepository badgeRepository,
        final ProjectRepository projectRepository,
        final TagRepository tagRepository,
        final UserRepository userRepository) {
        super(badgeRepository, projectRepository, tagRepository, userRepository);
    }

}
