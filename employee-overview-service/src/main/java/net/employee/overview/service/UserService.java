package net.employee.overview.service;

import net.employee.overview.dao.form.UserFilterForm;
import net.employee.overview.model.entity.User;

import java.util.List;

public interface UserService extends PersistableService {

    List<User> fetchUsersWithFilter(UserFilterForm userFilterForm);

    List<User> fetchAllUsers();

    User fetchUserById(Long id);
}
