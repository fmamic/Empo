package net.employee.overview.dao;

import java.util.List;

import net.employee.overview.model.entity.ActionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActionInfoRepository extends JpaRepository<ActionInfo, Long> {

    @Query("select a from #{#entityName} a order by a.m_timestamp desc")
    List<ActionInfo> findAllWithDateSort();

}
