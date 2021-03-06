package net.employee.overview.service.impl;

import java.util.List;

import net.employee.overview.dao.ActionInfoRepository;
import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserCriteria;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.dao.form.UserFilterForm;
import net.employee.overview.model.entity.User;
import net.employee.overview.service.AbstractEntityPersistableService;
import net.employee.overview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractEntityPersistableService implements UserService {

    private static final String UNCHECKED = "unchecked";

    private final UserCriteria m_userCriteria;

    @Autowired
    public UserServiceImpl(final BadgeRepository p_badgeRepository,
            final ProjectRepository p_projectRepository,
            final TagRepository p_tagRepository,
            final UserRepository p_userRepository, final UserCriteria p_userCriteria, final ActionInfoRepository p_actionInfoRepository) {

        super(p_badgeRepository, p_projectRepository, p_tagRepository, p_userRepository, p_actionInfoRepository);

        m_userCriteria = p_userCriteria;
    }

    @SuppressWarnings(UNCHECKED)
    public final User fetchUserByUsername(final String p_username) {
        return ((UserRepository) getRepository(User.class)).findUserByUsername(p_username);
    }

    @SuppressWarnings(UNCHECKED)
    public final List<User> fetchAllUsers() {
        return ((UserRepository) getRepository(User.class)).findAll();
    }

    @SuppressWarnings(UNCHECKED)
    public final User fetchUserById(final Long p_id) {
        return (User) getRepository(User.class).findOne(p_id);
    }

    @SuppressWarnings(UNCHECKED)
    public final List<User> fetchUsersWithFilter(final UserFilterForm p_userFilterForm) {
        return m_userCriteria.fetchUsersWithFilter(p_userFilterForm);
    }

}
