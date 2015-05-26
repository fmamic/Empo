package net.employee.overview.service;

import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.TagType;

import java.util.List;

public interface TagService extends PersistableService {

    TagType fetchOneTagType(Long id);

    List<TagType> fetchAllTagType();

    List<Tag> fetchAllTags();

    List<Tag> fetchTagsByIds(List<Long> ids);


    }
