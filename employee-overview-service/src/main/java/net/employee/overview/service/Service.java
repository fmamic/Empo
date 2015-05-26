package net.employee.overview.service;

import net.employee.overview.model.Identifier;

import java.util.List;

public interface Service<T extends Identifier> {

    List findAll(Class<T> type);

    T getOne(Class<T> type, Long id);

}
