package net.employee.overview.service.impl;

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

import java.util.List;

@Service
public class UserServiceImpl extends AbstractPersistableService implements UserService {

    private final UserCriteria userCriteria;

    @Autowired
    public UserServiceImpl(final BadgeRepository badgeRepository,
            final ProjectRepository projectRepository,
            final TagRepository tagRepository,
            final UserRepository userRepository, final UserCriteria userCriteria) {
        super(badgeRepository, projectRepository, tagRepository, userRepository);
        this.userCriteria = userCriteria;
    }

    @SuppressWarnings("unchecked")
    public final List<User> fetchAllUsers() {
        return ((UserRepository)getRepository(User.class)).findAll();
    }

    public final User fetchUserById(final Long id) {
        return (User) getRepository(User.class).findOne(id);
    }

    public final List<User> fetchUsersWithFilter(final UserFilterForm userFilterForm) {
        return userCriteria.fetchUsersWithFilter(userFilterForm);
    }

}
