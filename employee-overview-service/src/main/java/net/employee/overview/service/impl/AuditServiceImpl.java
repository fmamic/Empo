package net.employee.overview.service.impl;

import net.employee.overview.dao.AuditRepository;
import net.employee.overview.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditRepository auditRepository;

    @Override
    public List<Object[]> entityRevisions(final Class<?> type) {
        return auditRepository.entityRevisions(type);
    }
}
