package net.employee.overview.service;

import java.util.List;

import net.employee.overview.model.entity.ActionInfo;

public interface ActionInfoService extends PersistableService {

    List<ActionInfo> findAllWithSort();

}
