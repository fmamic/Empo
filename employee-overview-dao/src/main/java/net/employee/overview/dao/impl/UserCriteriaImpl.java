package net.employee.overview.dao.impl;

import net.employee.overview.dao.UserCriteria;
import net.employee.overview.dao.form.UserFilterForm;
import net.employee.overview.model.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserCriteriaImpl implements UserCriteria {

    @PersistenceContext
    private EntityManager entityManager;

    public final List<User> fetchUsersWithFilter(final UserFilterForm userFilterForm) {
        final Session session = entityManager.unwrap(Session.class);
        final Criteria criteria = session.createCriteria(User.class);

        if(userFilterForm.getName() != null) {
            criteria.add(Restrictions.eq("name", userFilterForm.getName()));
        }

        if(userFilterForm.getId() != null) {
            criteria.add(Restrictions.eq("id", userFilterForm.getId()));
        }

        //noinspection unchecked
        return criteria.list();
    }

}
