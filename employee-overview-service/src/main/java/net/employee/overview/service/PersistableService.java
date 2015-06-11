package net.employee.overview.service;

import net.employee.overview.model.AbstractPersistable;

public interface PersistableService<T extends AbstractPersistable> extends Service {

    T save(Class<T> p_type, T p_object);

    void delete(Class<T> p_type, T p_object);

}
