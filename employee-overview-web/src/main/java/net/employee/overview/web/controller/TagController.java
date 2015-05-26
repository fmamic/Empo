package net.employee.overview.web.controller;

import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.TagType;
import net.employee.overview.model.entity.User;
import net.employee.overview.service.TagService;
import net.employee.overview.service.UserService;
import net.employee.overview.web.form.TagForm;
import net.employee.overview.web.form.UserTagForm;
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

    private final TagService  tagService;
    private final UserService userService;

    @Autowired
    public TagController(final TagService p_tagService, final UserService p_userService) {
        tagService = p_tagService;
        userService = p_userService;
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

        for (final Tag tag : tags) {
            if(!user.getTags().contains(tag)) {
                user.getTags().add(tag);
            }
        }

        return userService.save(User.class, user) != null;
    }
}
