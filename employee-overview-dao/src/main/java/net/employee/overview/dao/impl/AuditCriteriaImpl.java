package net.employee.overview.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.employee.overview.dao.AuditCriteria;
import net.employee.overview.model.entity.Revinfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class AuditCriteriaImpl implements AuditCriteria {

    @PersistenceContext
    private EntityManager m_entityManager;

    @SuppressWarnings("unchecked")
    public final List<Revinfo> fetchEventsByDate() {
        final Session session = m_entityManager.unwrap(Session.class);
        final Criteria criteria = session.createCriteria(Revinfo.class);

        return criteria.list();
    }

}
