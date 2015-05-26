package net.employee.overview.web.form;

public class TagForm {

    private Long id;

    private Long version;

    private String name;

    private String description;

    private String displayName;

    private Long tagTypeId;

    private String link;

    private UserForm userForm;

    public Long getId() {
        return id;
    }

    public void setId(final Long p_id) {
        id = p_id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long p_version) {
        version = p_version;
    }

    public String getName() {
        return name;
    }

    public void setName(final String p_name) {
        name = p_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String p_description) {
        description = p_description;
    }

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(final UserForm p_userForm) {
        userForm = p_userForm;
    }

    public String getLink() {
        return link;
    }

    public void setLink(final String p_link) {
        link = p_link;
    }

    public Long getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(final Long p_tagTypeId) {
        tagTypeId = p_tagTypeId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String p_displayName) {
        displayName = p_displayName;
    }
}
