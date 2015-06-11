package net.employee.overview.dao;

import java.util.List;

import net.employee.overview.model.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    @Query("select b from Badge b where b.m_id in :ids")
    List<Badge> fetchBadgesByIds(@Param("ids") List<Long> p_ids);
}
