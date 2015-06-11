package net.employee.overview.service;

import java.util.List;
import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.TagType;

public interface TagService extends PersistableService {

    TagType fetchOneTagType(Long p_id);

    List<TagType> fetchAllTagType();

    List<Tag> fetchAllTags();

    List<Tag> fetchTagsByIds(List<Long> p_ids);


}
