package net.employee.overview.dao;

import java.util.List;

import net.employee.overview.dao.form.UserFilterForm;
import net.employee.overview.model.entity.User;

public interface UserCriteria {

    List<User> fetchUsersWithFilter(UserFilterForm p_userFilterForm);

}
