package net.employee.overview.web.controller;

import net.employee.overview.model.entity.Badge;
import net.employee.overview.model.entity.BadgeType;
import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.User;
import net.employee.overview.service.BadgeService;
import net.employee.overview.service.TagService;
import net.employee.overview.service.UserService;
import net.employee.overview.web.form.BadgeForm;
import net.employee.overview.web.form.BadgeTypeForm;
import net.employee.overview.web.form.UserBadgeForm;
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
public class BadgeController {

    private final BadgeService badgeService;
    private final UserService  userService;
    private final TagService   tagService;

    @Autowired
    public BadgeController(final BadgeService p_badgeService, final UserService p_userService, final TagService p_tagService) {
        badgeService = p_badgeService;
        userService = p_userService;
        tagService = p_tagService;
    }

    @RequestMapping("/search/badgetype/all")
    public @ResponseBody List<BadgeTypeForm> searchAllBadgeTypes() {
        final List<BadgeType> badgeTypes = badgeService.fetchAllBadgeType();
        final List<BadgeTypeForm> forms = new ArrayList<BadgeTypeForm>();

        for (final BadgeType type : badgeTypes) {
            BadgeTypeForm form = new BadgeTypeForm();

            BeanUtils.copyProperties(type, form);

            forms.add(form);
        }
        return forms;
    }

    @RequestMapping("/search/badges/all")
    public @ResponseBody List<BadgeForm> searchAllBadges() {
        final List<Badge> badges = badgeService.fetchAllBadges();
        final List<BadgeForm> forms = new ArrayList<BadgeForm>();

        for (final Badge badge : badges) {
            BadgeForm form = new BadgeForm();
            BeanUtils.copyProperties(badge, form);
            forms.add(form);
        }

        return forms;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/save/badge")
    public @ResponseBody boolean saveBadge(@RequestBody BadgeForm badgeForm) {
        final Badge badge = new Badge();
        BeanUtils.copyProperties(badgeForm, badge);

        badge.setCreator((User) userService.getOne(User.class, badgeForm.getUserId()));
        badge.setBadgeType(badgeService.fetchBadgeType(badgeForm.getBadgeTypeId()));
        badge.setTags((Tag) tagService.getOne(Tag.class, badgeForm.getTagId()));

        return badgeService.save(Badge.class, badge) != null;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/save/user/badges", method = RequestMethod.POST)
    public @ResponseBody boolean saveBadgesForUser(@RequestBody final UserBadgeForm userBadgeForm) {
        final User user = userService.fetchUserById(userBadgeForm.getPersonId());

        final List<Badge> badges = badgeService.fetchBadgesByIds(userBadgeForm.getBadgesId());

        for (final Badge badge : badges) {
            if(!user.getBadges().contains(badge)) {
                user.getBadges().add(badge);
            }
        }

        return userService.save(User.class, user) != null;
    }
}
