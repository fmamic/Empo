package net.employee.overview.service.impl;

import net.employee.overview.dao.ActionInfoRepository;
import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.service.AbstractEntityPersistableService;
import net.employee.overview.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends AbstractEntityPersistableService implements ProjectService {

    @Autowired
    public ProjectServiceImpl(final BadgeRepository p_badgeRepository,
            final ProjectRepository p_projectRepository,
            final TagRepository p_tagRepository,
            final UserRepository p_userRepository, final ActionInfoRepository p_actionInfoRepository) {

        super(p_badgeRepository, p_projectRepository, p_tagRepository, p_userRepository, p_actionInfoRepository);
    }

}
