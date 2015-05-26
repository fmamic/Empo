package net.employee.overview.dao;

import net.employee.overview.model.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    @Query("select b from Badge b where b.id in :ids")
    public List<Badge> fetchBadgesByIds(@Param("ids") final List<Long> ids);
}
