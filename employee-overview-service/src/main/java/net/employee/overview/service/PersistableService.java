package net.employee.overview.service;

import net.employee.overview.model.Persistable;

public interface PersistableService<T extends Persistable> extends Service {

    T save(Class<T> type, T object);

    void delete(Class<T> type, T object);

}
