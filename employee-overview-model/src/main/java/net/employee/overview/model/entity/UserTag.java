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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Audited
@Table(name = "EMP_USR_TAG")
@SequenceGenerator(name = "USR_TAG_SEQ", sequenceName = "USR_TAG_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "PRO_VERSION"))
public class UserTag extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USR_TAG_SEQ")
    @Column(name = "USR_TAG_ID")
    private Long m_id;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "USR_ID", foreignKey = @ForeignKey(name = "USR_TAG_FK"))
    private User m_user;

    @ManyToOne
    @JoinColumn(name = "TAG_ID", foreignKey = @ForeignKey(name = "TAG_USR_FK"))
    private Tag m_tag;

    public User getUser() {
        return m_user;
    }

    public void setUser(final User p_user) {
        m_user = p_user;
    }

    public Tag getTag() {
        return m_tag;
    }

    public void setTag(final Tag p_tag) {
        m_tag = p_tag;
    }

    public Long getId() {
        return m_id;
    }

    public void setId(final Long p_id) {
        m_id = p_id;
    }

    @Override
    public final boolean equals(final Object p_obj) {
        if (p_obj == this) {
            return true;
        }
        if (!(p_obj instanceof UserTag)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final UserTag casted = (UserTag) p_obj;

        if (getId() != null) {
            final Long otherId = casted.getId();
            if (otherId != null) {
                builder.append(getId(), casted.getId());
                return builder.isEquals();
            }
        }

        builder.append(getUser(), casted.getUser());
        builder.append(getTag(), casted.getTag());

        return builder.isEquals();
    }

    @Override
    public final int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getUser());
        builder.append(getTag());

        return builder.toHashCode();
    }
}
