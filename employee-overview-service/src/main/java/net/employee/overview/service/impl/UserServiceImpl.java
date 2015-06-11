package net.employee.overview.service.impl;

import java.util.List;

import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserCriteria;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.dao.form.UserFilterForm;
import net.employee.overview.model.entity.User;
import net.employee.overview.service.AbstractPersistableService;
import net.employee.overview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractPersistableService implements UserService {

    private final UserCriteria m_userCriteria;

    @Autowired
    public UserServiceImpl(final BadgeRepository p_badgeRepository,
            final ProjectRepository p_projectRepository,
            final TagRepository p_tagRepository,
            final UserRepository p_userRepository, final UserCriteria p_userCriteria) {

        super(p_badgeRepository, p_projectRepository, p_tagRepository, p_userRepository);

        m_userCriteria = p_userCriteria;
    }

    @SuppressWarnings("unchecked")
    public final List<User> fetchAllUsers() {
        return ((UserRepository) getRepository(User.class)).findAll();
    }

    @SuppressWarnings("unchecked")
    public final User fetchUserById(final Long p_id) {
        return (User) getRepository(User.class).findOne(p_id);
    }

    @SuppressWarnings("unchecked")
    public final List<User> fetchUsersWithFilter(final UserFilterForm p_userFilterForm) {
        return m_userCriteria.fetchUsersWithFilter(p_userFilterForm);
    }

}
