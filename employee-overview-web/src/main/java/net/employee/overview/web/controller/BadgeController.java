package net.employee.overview.web.controller;

import java.util.ArrayList;
import java.util.List;

import net.employee.overview.model.entity.Badge;
import net.employee.overview.model.entity.BadgeTag;
import net.employee.overview.model.entity.BadgeType;
import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.User;
import net.employee.overview.model.entity.UserBadge;
import net.employee.overview.service.BadgeService;
import net.employee.overview.service.BadgeTagService;
import net.employee.overview.service.TagService;
import net.employee.overview.service.UserBadgeService;
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

@RestController
public class BadgeController {

    private final BadgeService     badgeService;
    private final UserService      userService;
    private final TagService       tagService;
    private final UserBadgeService userBadgeService;
    private final BadgeTagService  badgeTagService;

    @Autowired
    public BadgeController(final BadgeService badgeService, final UserService userService,
            final TagService tagService, UserBadgeService userBadgeService,
            BadgeTagService badgeTagService) {
        this.badgeService = badgeService;
        this.userService = userService;
        this.tagService = tagService;
        this.userBadgeService = userBadgeService;
        this.badgeTagService = badgeTagService;
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

        final BadgeTag badgeTag = new BadgeTag();
        badgeTag.setBadge(badge);
        badgeTag.setTag((Tag) tagService.getOne(Tag.class, badgeForm.getTagId()));

        return badgeTagService.save(BadgeTag.class, badgeTag) != null;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/save/user/badges", method = RequestMethod.POST)
    public @ResponseBody boolean saveBadgesForUser(@RequestBody final UserBadgeForm userBadgeForm) {

        final User user = (User) userService.getOne(User.class, userBadgeForm.getPersonId());

        for (final Long badgeId : userBadgeForm.getBadgesId()) {
            final UserBadge userBadge = new UserBadge();
            userBadge.setBadge((Badge) badgeService.getOne(Badge.class, badgeId));
            userBadge.setUser(user);

            if(!user.getBadges().contains(userBadge)) {
                userBadgeService.save(UserBadge.class, userBadge);
            }
        }
        return true;
    }
}
