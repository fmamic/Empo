package net.employee.overview.service;

import java.util.List;
import net.employee.overview.model.Identifier;

public interface Service<T extends Identifier> {

    List findAll(Class<T> p_type);

    T getOne(Class<T> p_type, Long p_id);

}
