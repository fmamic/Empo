package net.employee.overview.web.controller;

import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.TagType;
import net.employee.overview.model.entity.User;
import net.employee.overview.service.AuditService;
import net.employee.overview.service.TagService;
import net.employee.overview.service.UserService;
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
import java.util.Date;
import java.util.List;

@RestController
public class TagController {

    private final TagService   tagService;
    private final UserService  userService;
    private final AuditService auditService;

    @Autowired
    public TagController(final TagService p_tagService, final UserService p_userService, final AuditService p_auditService) {
        tagService = p_tagService;
        userService = p_userService;
        auditService = p_auditService;
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
        final User user = userService.fetchUserById(userTagForm.getPersonId());

        final List<Tag> tags = tagService.fetchTagsByIds(userTagForm.getTagsId());

//        for (final Tag tag : tags) {
//            if (!user.getTags().contains(tag)) {
//                user.getTags().add(tag);
//            }
//        }

        return userService.save(User.class, user) != null;
    }
}
