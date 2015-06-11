package net.employee.overview.web.controller;

import java.util.ArrayList;
import java.util.List;

import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.TagType;
import net.employee.overview.model.entity.User;
import net.employee.overview.model.entity.UserTag;
import net.employee.overview.service.AuditService;
import net.employee.overview.service.TagService;
import net.employee.overview.service.UserService;
import net.employee.overview.service.UserTagService;
import net.employee.overview.web.form.RevisionForm;
import net.employee.overview.web.form.TagForm;
import net.employee.overview.web.form.UserTagForm;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

    private final TagService     m_tagService;
    private final UserService    m_userService;
    private final AuditService   m_auditService;
    private final UserTagService m_userTagService;

    @Autowired
    public TagController(final TagService p_tagService, final UserService p_userService,
            final AuditService p_auditService, final UserTagService p_userTagService) {
        m_tagService = p_tagService;
        m_userService = p_userService;
        m_auditService = p_auditService;
        m_userTagService = p_userTagService;
    }

    @RequestMapping("/search/tagtype/all")
    public final @ResponseBody List<TagType> searchAllTagTypes() {
        return m_tagService.fetchAllTagType();
    }

    @RequestMapping("/search/tags/all")
    public final @ResponseBody List<TagForm> searchAllTags() {
        final List<Tag> tags = m_tagService.fetchAllTags();
        final List<TagForm> forms = new ArrayList<TagForm>();

        for (final Tag tag : tags) {
            final TagForm tagForm = new TagForm();
            BeanUtils.copyProperties(tag, tagForm);

            forms.add(tagForm);
        }
        return forms;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/tag/revisions")
    public final @ResponseBody List<RevisionForm<TagForm>> tagRevisions() {
        final List<Object[]> tags = m_auditService.entityRevisions(Tag.class);
        final List<RevisionForm<TagForm>> forms = new ArrayList<RevisionForm<TagForm>>();

        for (final Object[] tag : tags) {
            final TagForm tagForm = new TagForm();
            BeanUtils.copyProperties(tag[0], tagForm);

            final RevisionForm revisionForm = new RevisionForm();
            revisionForm.setForm(tagForm);
            revisionForm.setRevisionDate(((DefaultRevisionEntity) tag[1]).getRevisionDate());
            revisionForm.setRevisionType(((RevisionType) tag[2]).name());
            revisionForm.setType("TAG");

            forms.add(revisionForm);
        }
        return forms;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/save/tag", method = RequestMethod.POST)
    public final @ResponseBody boolean saveTag(@RequestBody final TagForm p_tagForm) {
        final Tag tag = new Tag();

        tag.setTagType(m_tagService.fetchOneTagType(p_tagForm.getTagTypeId()));
        BeanUtils.copyProperties(p_tagForm, tag);

        return m_tagService.save(Tag.class, tag) != null;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/save/user/tags", method = RequestMethod.POST)
    public final @ResponseBody boolean saveTagForUser(@RequestBody final UserTagForm p_userTagForm) {
        final User user = (User) m_userService.getOne(User.class, p_userTagForm.getPersonId());

        for (final Long tagId : p_userTagForm.getTagsId()) {
            final UserTag userTag = new UserTag();
            userTag.setTag((Tag) m_tagService.getOne(Tag.class, tagId));
            userTag.setUser(user);

            if (!user.getTags().contains(userTag)) {
                m_userTagService.save(UserTag.class, userTag);
            }
        }
        return true;
    }
}
