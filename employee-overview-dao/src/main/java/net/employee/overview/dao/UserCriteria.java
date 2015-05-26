package net.employee.overview.dao;

import net.employee.overview.dao.form.UserFilterForm;
import net.employee.overview.model.entity.User;

import java.util.List;

public interface UserCriteria {

    List<User> fetchUsersWithFilter(UserFilterForm userFilterForm);

}
