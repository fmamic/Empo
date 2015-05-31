package net.employee.overview.dao.impl;

import net.employee.overview.dao.AuditCriteria;
import net.employee.overview.model.entity.Revinfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class AuditCriteriaImpl implements AuditCriteria {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object> fetchEventsByDate() {
        final Session session = entityManager.unwrap(Session.class);
        final Criteria criteria = session.createCriteria(Revinfo.class);

        return null;
    }

}
