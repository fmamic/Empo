package net.employee.overview.dao;

import java.util.List;

public interface AuditRepository {

    List<Object[]> entityRevisions(Class<?> p_type);

}
