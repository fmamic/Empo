package net.employee.overview.web.form;

import java.util.Date;

public class ActionInfoForm extends Form {

    private String action;

    private Long userId;

    private Long entityId;

    private String type;

    private Date timestamp;

    private String userName;

    private String entityName;

    public final String getAction() {
        return action;
    }

    public final void setAction(final String p_action) {
        action = p_action;
    }

    public final Long getUserId() {
        return userId;
    }

    public final void setUserId(final Long p_userId) {
        userId = p_userId;
    }

    public final Long getEntityId() {
        return entityId;
    }

    public final void setEntityId(final Long p_entityId) {
        entityId = p_entityId;
    }

    public final String getType() {
        return type;
    }

    public final void setType(final String p_type) {
        type = p_type;
    }

    public final Date getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(final Date p_timestamp) {
        timestamp = p_timestamp;
    }

    public final String getUserName() {
        return userName;
    }

    public final void setUserName(final String p_userName) {
        userName = p_userName;
    }

    public final String getEntityName() {
        return entityName;
    }

    public final void setEntityName(final String p_entityName) {
        entityName = p_entityName;
    }
}
