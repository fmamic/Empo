package net.employee.overview.service;

import java.util.List;
import net.employee.overview.dao.form.UserFilterForm;
import net.employee.overview.model.entity.User;

public interface UserService extends PersistableService {

    User fetchUserByUsername(final String p_username);

    List<User> fetchUsersWithFilter(UserFilterForm p_userFilterForm);

    List<User> fetchAllUsers();

    User fetchUserById(Long p_id);
}
