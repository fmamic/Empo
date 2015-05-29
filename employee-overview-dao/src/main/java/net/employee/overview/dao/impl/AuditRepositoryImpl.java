package net.employee.overview.dao.impl;

import net.employee.overview.dao.AuditRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class AuditRepositoryImpl implements AuditRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> entityRevisions(final Class<?> type) {
        final AuditReader auditReader = AuditReaderFactory.get(entityManager);
        final AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(type, false, true);

        return query.getResultList();
    }
}
