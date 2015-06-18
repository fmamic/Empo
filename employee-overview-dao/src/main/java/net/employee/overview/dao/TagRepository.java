package net.employee.overview.dao;

import java.util.List;

import net.employee.overview.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select t from #{#entityName} t where t.m_id in :ids")
    List<Tag> fetchTagsByIds(@Param("ids") final List<Long> p_ids);

}
