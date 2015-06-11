package net.employee.overview.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.employee.overview.dao.UserCriteria;
import net.employee.overview.dao.form.UserFilterForm;
import net.employee.overview.model.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class UserCriteriaImpl implements UserCriteria {

    @PersistenceContext
    private EntityManager m_entityManager;

    @SuppressWarnings("unchecked")
    public final List<User> fetchUsersWithFilter(final UserFilterForm p_userFilterForm) {
        final Session session = m_entityManager.unwrap(Session.class);
        final Criteria criteria = session.createCriteria(User.class);

        if (p_userFilterForm.getName() != null) {
            criteria.add(Restrictions.eq("name", p_userFilterForm.getName()));
        }

        if (p_userFilterForm.getId() != null) {
            criteria.add(Restrictions.eq("id", p_userFilterForm.getId()));
        }

        return criteria.list();
    }

}
