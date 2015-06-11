package net.employee.overview.service.impl;

import java.util.List;

import net.employee.overview.dao.AuditRepository;
import net.employee.overview.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditRepository m_auditRepository;

    @Override
    public final List<Object[]> entityRevisions(final Class<?> p_type) {
        return m_auditRepository.entityRevisions(p_type);
    }
}
