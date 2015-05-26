package net.employee.overview.dao;

import net.employee.overview.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select t from Tag t where t.id in :ids")
    public List<Tag> fetchTagsByIds(@Param("ids") final List<Long> ids);

}
