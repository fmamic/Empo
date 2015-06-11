package net.employee.overview.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.employee.overview.dao.AuditRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Component;

@Component
public class AuditRepositoryImpl implements AuditRepository {

    @PersistenceContext
    private EntityManager m_entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> entityRevisions(final Class<?> p_type) {
        final AuditReader auditReader = AuditReaderFactory.get(m_entityManager);
        final AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(p_type, false, true);

        return query.getResultList();
    }
}
