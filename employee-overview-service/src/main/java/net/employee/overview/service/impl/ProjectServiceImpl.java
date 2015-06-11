package net.employee.overview.service.impl;

import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.service.AbstractPersistableService;
import net.employee.overview.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends AbstractPersistableService implements ProjectService {

    @Autowired
    public ProjectServiceImpl(final BadgeRepository p_badgeRepository,
            final ProjectRepository p_projectRepository,
            final TagRepository p_tagRepository,
            final UserRepository p_userRepository) {

        super(p_badgeRepository, p_projectRepository, p_tagRepository, p_userRepository);
    }

}
