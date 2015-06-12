package net.employee.overview.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.employee.overview.dao.AuditRepository;
import net.employee.overview.model.entity.Revinfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditRepositoryImpl implements AuditRepository {

    @PersistenceContext
    private EntityManager m_entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> entityRevisions(final Class<?> p_type) {
        final AuditReader auditReader = AuditReaderFactory.get(m_entityManager);

        final Session session = m_entityManager.unwrap(Session.class);
        final Criteria criteria = session.createCriteria(Revinfo.class);
        criteria.addOrder(Order.desc("m_revtstmp"));
        criteria.setMaxResults(10);

        final List<Revinfo> revInfos = criteria.list();

        final List<Object> revisions = new ArrayList<Object>();

        for (final Revinfo revinfo : revInfos) {
            revisions.addAll(auditReader
                    .getCrossTypeRevisionChangesReader().findEntities(revinfo.getRev()));
        }

        return null;
    }
}
