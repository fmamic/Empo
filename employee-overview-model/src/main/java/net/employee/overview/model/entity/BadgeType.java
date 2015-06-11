package net.employee.overview.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.employee.overview.model.AbstractPersistable;
import net.employee.overview.model.enums.BadgePermission;

@Entity
@Table(name = "EMP_BADGE_TYPE")
@SequenceGenerator(name = "BDT_SEQ", sequenceName = "BDT_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "BDT_VERSION"))
public class BadgeType extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BDT_SEQ")
    @Column(name = "BDT_ID")
    private Long m_id;

    @Column(name = "BDT_NAME")
    private String m_name;

    @ManyToOne
    @JoinColumn(name = "BDT_USR_ID", foreignKey = @ForeignKey(name = "BDT_USR_FK"))
    private User m_owner;

    @Column(name = "BDT_PERMISSION")
    private BadgePermission m_badgePermission;

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }

    public Long getId() {
        return m_id;
    }

    public void setId(final Long p_id) {
        m_id = p_id;
    }

    public BadgePermission getBadgePermission() {
        return m_badgePermission;
    }

    public void setBadgePermission(final BadgePermission p_badgePermission) {
        m_badgePermission = p_badgePermission;
    }

    public User getOwner() {
        return m_owner;
    }

    public void setOwner(final User p_owner) {
        m_owner = p_owner;
    }
}
