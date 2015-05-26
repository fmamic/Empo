package net.employee.overview.service.impl;

import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.TagTypeRepository;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.TagType;
import net.employee.overview.service.AbstractPersistableService;
import net.employee.overview.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends AbstractPersistableService implements TagService {

    private final TagTypeRepository tagTypeRepository;

    @Autowired
    public TagServiceImpl(final BadgeRepository badgeRepository,
            final ProjectRepository projectRepository,
            final TagRepository tagRepository,
            final UserRepository userRepository, final TagTypeRepository p_tagTypeRepository) {
        super(badgeRepository, projectRepository, tagRepository, userRepository);
        tagTypeRepository = p_tagTypeRepository;
    }

    public TagType fetchOneTagType(final Long id) {
        return tagTypeRepository.findOne(id);
    }

    public List<Tag> fetchTagsByIds(final List<Long> ids) {
        return ((TagRepository)getRepository(Tag.class)).fetchTagsByIds(ids);
    }

    public List<Tag> fetchAllTags() {
        return getRepository(Tag.class).findAll();
    }

    public List<TagType> fetchAllTagType() {
        return tagTypeRepository.findAll();
    }
}
