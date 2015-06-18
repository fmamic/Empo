package net.employee.overview.service;

import com.google.common.collect.ImmutableMap;
import net.employee.overview.dao.ActionInfoRepository;
import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.model.AbstractPersistable;
import net.employee.overview.model.entity.ActionInfo;
import net.employee.overview.model.entity.Badge;
import net.employee.overview.model.entity.Project;
import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEntityPersistableService<T extends AbstractPersistable> extends AbstractPersistableService {

    private final BadgeRepository      m_badgeRepository;
    private final ProjectRepository    m_projectRepository;
    private final TagRepository        m_tagRepository;
    private final UserRepository       m_userRepository;
    private final ActionInfoRepository m_actionInfoRepository;

    @Autowired
    public AbstractEntityPersistableService(final BadgeRepository p_badgeRepository,
            final ProjectRepository p_projectRepository, final TagRepository p_tagRepository,
            final UserRepository p_userRepository, final ActionInfoRepository p_actionInfoRepository) {

        m_badgeRepository = p_badgeRepository;
        m_projectRepository = p_projectRepository;
        m_tagRepository = p_tagRepository;
        m_userRepository = p_userRepository;
        m_actionInfoRepository = p_actionInfoRepository;

        setRepositories(
                ImmutableMap.of(Badge.class, m_badgeRepository, Project.class, m_projectRepository,
                        Tag.class, m_tagRepository, User.class, m_userRepository, ActionInfo.class, m_actionInfoRepository));
    }
}
