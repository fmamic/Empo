package net.employee.overview.dao;

import java.util.List;
import net.employee.overview.model.entity.Revinfo;

public interface AuditCriteria {

    List<Revinfo> fetchEventsByDate();

}
