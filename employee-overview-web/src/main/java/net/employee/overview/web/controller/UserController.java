package net.employee.overview.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.employee.overview.dao.form.UserFilterForm;
import net.employee.overview.model.entity.User;
import net.employee.overview.model.entity.UserBadge;
import net.employee.overview.model.entity.UserTag;
import net.employee.overview.service.AuditService;
import net.employee.overview.service.UserService;
import net.employee.overview.web.form.BadgeForm;
import net.employee.overview.web.form.RevisionForm;
import net.employee.overview.web.form.TagForm;
import net.employee.overview.web.form.UserForm;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final String DATE_FORMAT = "dd.MM.yyyy";

    private final UserService  m_userService;
    private final AuditService m_auditService;

    @Autowired
    public UserController(final UserService p_userService, final AuditService p_auditService) {
        m_userService = p_userService;
        m_auditService = p_auditService;
    }

    @RequestMapping("/search/user/all")
    public final @ResponseBody List<UserForm> searchAllUser() {
        final List<User> users = m_userService.fetchAllUsers();
        return getUserForms(users);
    }

    @RequestMapping("/search/user/{id}")
    public final @ResponseBody UserForm searchUserById(@PathVariable("id") final Long p_id) {
        final User user = m_userService.fetchUserById(p_id);

        return setUserForm(user);
    }

    @RequestMapping("/search/user")
    public final @ResponseBody List<UserForm> searchUser(@RequestBody final UserFilterForm p_userFilterForm) {
        final List<User> users = m_userService.fetchUsersWithFilter(p_userFilterForm);
        return getUserForms(users);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/user/revisions")
    public final @ResponseBody List<RevisionForm<UserForm>> searchUserRevision() {
        final List<Object[]> users = m_auditService.entityRevisions(User.class);
        final List<RevisionForm<UserForm>> forms = new ArrayList<RevisionForm<UserForm>>();

        for (final Object[] user : users) {
            final UserForm userForm = setUserFormRevision((User) user[0]);

            final RevisionForm revisionForm = new RevisionForm();
            revisionForm.setForm(userForm);
            revisionForm.setRevisionDate(((DefaultRevisionEntity) user[1]).getRevisionDate());
            revisionForm.setRevisionType(((RevisionType) user[2]).name());
            revisionForm.setType("USER");

            userForm.setRole(null);
            forms.add(revisionForm);
        }

        return forms;
    }

    private List<UserForm> getUserForms(final List<User> p_users) {
        final List<UserForm> userForms = new ArrayList<UserForm>();

        for (final User user : p_users) {
            final UserForm userForm = setUserForm(user);

            userForms.add(userForm);
        }

        return userForms;
    }

    private UserForm setUserFormRevision(final User p_user) {
        final UserForm userForm = new UserForm();
        setTagsBadges(p_user, userForm);

        return userForm;
    }

    @SuppressWarnings("RedundantStringToString")
    private void setTagsBadges(final User p_user, final UserForm p_userForm) {
        BeanUtils.copyProperties(p_user, p_userForm);

        p_userForm.setDateOfBirthStr((new SimpleDateFormat(DATE_FORMAT).format(p_user.getDateOfBirth())).toString());

        for (final UserTag tag : p_user.getTags()) {
            final TagForm tagForm = new TagForm();
            BeanUtils.copyProperties(tag.getTag(), tagForm);

            p_userForm.getTagForm().add(tagForm);
        }

        for (final UserBadge badge : p_user.getBadges()) {
            final BadgeForm form = new BadgeForm();
            BeanUtils.copyProperties(badge.getBadge(), form);
            p_userForm.getBadgeForm().add(form);
        }
    }

    private UserForm setUserForm(final User p_user) {
        final UserForm userForm = new UserForm();
        final UserForm managerForm = new UserForm();

        setTagsBadges(p_user, userForm);

        if (p_user.getManager() != null) {
            BeanUtils.copyProperties(p_user.getManager(), managerForm);
        }

        userForm.setManagerForm(managerForm);
        return userForm;
    }
}
