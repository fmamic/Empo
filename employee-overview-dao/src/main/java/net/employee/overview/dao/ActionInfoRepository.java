package net.employee.overview.dao;

import net.employee.overview.model.entity.ActionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActionInfoRepository extends JpaRepository<ActionInfo, Long> {

    @Query("select a from ActionInfo a order by a.m_timestamp desc")
    List<ActionInfo> findAllWithDateSort();

}
