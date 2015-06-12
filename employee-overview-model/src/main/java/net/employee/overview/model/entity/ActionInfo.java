package net.employee.overview.model.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.employee.overview.model.AbstractPersistable;

@Entity
@Table(name = "EMP_ACTION_INFO")
@SequenceGenerator(name = "ACT_SEQ", sequenceName = "ACT_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "ACT_VERSION"))
public class ActionInfo extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACT_SEQ")
    @Column(name = "ACT_ID")
    private Long m_id;

    @Column(name = "ACT_ACTION")
    private String m_action;

    @Column(name = "ACT_USER_ID")
    private Long m_userId;

    @Column(name = "ACT_ENTITY_ID")
    private Long m_entityId;

    @Column(name = "ACT_TYPE")
    private String m_type;

    @Column(name = "ACT_TIMESTAMP")
    private Date m_timestamp;

    @Column(name = "ACT_USER_NAME")
    private String m_userName;

    @Column(name = "ACT_ENTITY_NAME")
    private String m_entityName;

    public Long getId() {
        return m_id;
    }

    public void setId(final Long p_id) {
        m_id = p_id;
    }

    public String getAction() {
        return m_action;
    }

    public void setAction(final String p_action) {
        m_action = p_action;
    }

    public Long getUserId() {
        return m_userId;
    }

    public void setUserId(final Long p_userId) {
        m_userId = p_userId;
    }

    public Long getEntityId() {
        return m_entityId;
    }

    public void setEntityId(final Long p_entityId) {
        m_entityId = p_entityId;
    }

    public String getType() {
        return m_type;
    }

    public void setType(final String p_type) {
        m_type = p_type;
    }

    public Date getTimestamp() {
        return m_timestamp;
    }

    public void setTimestamp(final Date p_timestamp) {
        m_timestamp = p_timestamp;
    }

    public String getUserName() {
        return m_userName;
    }

    public void setUserName(final String p_userName) {
        m_userName = p_userName;
    }

    public String getEntityName() {
        return m_entityName;
    }

    public void setEntityName(final String p_entityName) {
        m_entityName = p_entityName;
    }
}
