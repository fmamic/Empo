package net.employee.overview.web.form;

import net.employee.overview.model.code.Role;

import java.util.ArrayList;
import java.util.List;

public class UserForm {

    private Long id;

    private String username;

    private String picture;

    private String password;

    private String name;

    private String position;

    private String phone;

    private String cellPhone;

    private String description;

    private String email;

    private String workPlace;

    private String dateOfBirthStr;

    private List<TagForm> tagForm = new ArrayList<TagForm>();

    private List<BadgeForm> badgeForm = new ArrayList<BadgeForm>();

    private Role role;

    private UserForm managerForm;

    public Long getId() {
        return id;
    }

    public void setId(final Long p_id) {
        id = p_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String p_username) {
        username = p_username;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(final String p_picture) {
        picture = p_picture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String p_password) {
        password = p_password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String p_position) {
        position = p_position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String p_phone) {
        phone = p_phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(final String p_cellPhone) {
        cellPhone = p_cellPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String p_description) {
        description = p_description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String p_email) {
        email = p_email;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(final String p_workPlace) {
        workPlace = p_workPlace;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role p_role) {
        role = p_role;
    }

    public UserForm getManagerForm() {
        return managerForm;
    }

    public void setManagerForm(final UserForm p_managerForm) {
        managerForm = p_managerForm;
    }

    public String getName() {
        return name;
    }

    public void setName(final String p_name) {
        name = p_name;
    }

    public String getDateOfBirthStr() {
        return dateOfBirthStr;
    }

    public void setDateOfBirthStr(final String p_dateOfBirthStr) {
        dateOfBirthStr = p_dateOfBirthStr;
    }

    public List<TagForm> getTagForm() {
        return tagForm;
    }

    public void setTagForm(final List<TagForm> p_tagForm) {
        tagForm = p_tagForm;
    }

    public List<BadgeForm> getBadgeForm() {
        return badgeForm;
    }

    public void setBadgeForm(final List<BadgeForm> p_badgeForm) {
        badgeForm = p_badgeForm;
    }
}
