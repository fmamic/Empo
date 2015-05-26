package net.employee.overview.web.form;

import java.util.Date;

public class BadgeForm {

    private Long id;

    private String name;

    private Long tagId;

    private Long badgeTypeId;

    private Integer level;

    private String description;

    private Long userId;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(final Long p_id) {
        id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String p_name) {
        name = p_name;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(final Long p_tagId) {
        tagId = p_tagId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(final Integer p_level) {
        level = p_level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String p_description) {
        description = p_description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long p_userId) {
        userId = p_userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date p_date) {
        date = p_date;
    }

    public Long getBadgeTypeId() {
        return badgeTypeId;
    }

    public void setBadgeTypeId(final Long p_badgeTypeId) {
        badgeTypeId = p_badgeTypeId;
    }
}
