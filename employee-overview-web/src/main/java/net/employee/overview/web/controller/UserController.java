package net.employee.overview.web.controller;

import net.employee.overview.dao.form.UserFilterForm;
import net.employee.overview.model.entity.Badge;
import net.employee.overview.model.entity.Tag;
import net.employee.overview.model.entity.User;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final AuditService auditService;

    @Autowired
    public UserController(final UserService p_userService, final AuditService p_auditService) {
        userService = p_userService;
        auditService = p_auditService;
    }

    @RequestMapping("/search/user/all")
    public @ResponseBody List<UserForm> searchAllUser() {
        List<User> users = userService.fetchAllUsers();
        return getUserForms(users);
    }

    @RequestMapping("/search/user/{id}")
    public @ResponseBody UserForm searchUserById(@PathVariable("id") final Long id) {
        User user = userService.fetchUserById(id);

        return setUserForm(user);
    }

    @RequestMapping("/search/user")
    public @ResponseBody List<UserForm> searchUser(@RequestBody UserFilterForm userFilterForm) {
        List<User> users = userService.fetchUsersWithFilter(userFilterForm);
        return getUserForms(users);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/user/revisions")
    public @ResponseBody List<RevisionForm<UserForm>> searchUserRevision() {
        List<Object[]> users = auditService.entityRevisions(User.class);
        final List<RevisionForm<UserForm>> forms = new ArrayList<RevisionForm<UserForm>>();

        for (final Object[] user : users) {
            final UserForm userForm = setUserFormRevision((User)user[0]);

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
        List<UserForm> userForms = new ArrayList<UserForm>();

        for (final User user : p_users) {
            final UserForm userForm = setUserForm(user);

            userForms.add(userForm);
        }

        return userForms;
    }

    private UserForm setUserFormRevision(final User user) {
        final UserForm userForm = new UserForm();
        final UserForm managerForm = new UserForm();

        setTagsBadges(user, userForm);

        return userForm;
    }

    private void setTagsBadges(final User user, final UserForm p_userForm) {
        BeanUtils.copyProperties(user, p_userForm);

        //noinspection RedundantStringToString
        p_userForm.setDateOfBirthStr((new SimpleDateFormat("dd.MM.yyyy").format(user.getDateOfBirth())).toString());

//        for (final Tag tag : user.getTags()) {
//            final TagForm tagForm = new TagForm();
//            BeanUtils.copyProperties(tag, tagForm);
//
//            p_userForm.getTagForm().add(tagForm);
//        }
//
//        for (final Badge badge : user.getBadges()) {
//            BadgeForm form = new BadgeForm();
//            BeanUtils.copyProperties(badge, form);
//            p_userForm.getBadgeForm().add(form);
//        }
    }

    private UserForm setUserForm(final User user) {
        final UserForm userForm = new UserForm();
        final UserForm managerForm = new UserForm();

        setTagsBadges(user, userForm);

        if(user.getManager() != null) {
            BeanUtils.copyProperties(user.getManager(), managerForm);
        }

        userForm.setManagerForm(managerForm);
        return userForm;
    }
}
