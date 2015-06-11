package net.employee.overview.service.impl;

import java.util.List;

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

@Service
public class TagServiceImpl extends AbstractPersistableService implements TagService {

    private final TagTypeRepository m_tagTypeRepository;

    @Autowired
    public TagServiceImpl(final BadgeRepository p_badgeRepository,
            final ProjectRepository p_projectRepository,
            final TagRepository p_tagRepository,
            final UserRepository p_userRepository, final TagTypeRepository p_tagTypeRepository) {

        super(p_badgeRepository, p_projectRepository, p_tagRepository, p_userRepository);

        m_tagTypeRepository = p_tagTypeRepository;
    }

    public final TagType fetchOneTagType(final Long p_id) {
        return m_tagTypeRepository.findOne(p_id);
    }

    @SuppressWarnings("unchecked")
    public final List<Tag> fetchTagsByIds(final List<Long> p_ids) {
        return ((TagRepository) getRepository(Tag.class)).fetchTagsByIds(p_ids);
    }

    @SuppressWarnings("unchecked")
    public final List<Tag> fetchAllTags() {
        return getRepository(Tag.class).findAll();
    }

    public final List<TagType> fetchAllTagType() {
        return m_tagTypeRepository.findAll();
    }
}
