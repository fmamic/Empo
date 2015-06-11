package net.employee.overview.service;

import java.util.List;

public interface AuditService {

    List<Object[]> entityRevisions(Class<?> p_type);

}
