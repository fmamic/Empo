package net.employee.overview.web.controller;

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

import java.util.ArrayList;
import java.util.List;

@RestController
public class TagController {

    private final TagService   tagService;
    private final UserService  userService;
    private final AuditService auditService;
    private final UserTagService userTagService;

    @Autowired
    public TagController(final TagService tagService, final UserService userService,
        final AuditService auditService, final UserTagService userTagService) {
        this.tagService = tagService;
        this.userService = userService;
        this.auditService = auditService;
        this.userTagService = userTagService;
    }

    @RequestMapping("/search/tagtype/all")
    public @ResponseBody List<TagType> searchAllTagTypes() {
        return tagService.fetchAllTagType();
    }

    @RequestMapping("/search/tags/all")
    public @ResponseBody List<TagForm> searchAllTags() {
        final List<Tag> tags = tagService.fetchAllTags();
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
    public @ResponseBody List<RevisionForm<TagForm>> tagRevisions() {
        final List<Object[]> tags = auditService.entityRevisions(Tag.class);
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
    public @ResponseBody boolean saveTag(@RequestBody final TagForm tagForm) {
        final Tag tag = new Tag();

        tag.setTagType(tagService.fetchOneTagType(tagForm.getTagTypeId()));
        BeanUtils.copyProperties(tagForm, tag);

        return tagService.save(Tag.class, tag) != null;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/save/user/tags", method = RequestMethod.POST)
    public @ResponseBody boolean saveTagForUser(@RequestBody final UserTagForm userTagForm) {
        final User user = (User) userService.getOne(User.class, userTagForm.getPersonId());

        for (final Long tagId : userTagForm.getTagsId()) {
            final UserTag userTag = new UserTag();
            userTag.setTag((Tag) tagService.getOne(Tag.class, tagId));
            userTag.setUser(user);

            if(!user.getTags().contains(userTag)) {
                userTagService.save(UserTag.class, userTag);
            }
        }
        return true;
    }
}
