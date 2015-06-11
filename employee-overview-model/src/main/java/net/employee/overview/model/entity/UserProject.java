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
@Table(name = "EMP_USR_PRO")
@SequenceGenerator(name = "USR_PRO_SEQ", sequenceName = "USR_PRO_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "PRO_VERSION"))
public class UserProject extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USR_PRO_SEQ")
    @Column(name = "USR_PRO_ID")
    private Long m_id;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "USR_ID", foreignKey = @ForeignKey(name = "USR_PRO_FK"))
    private User m_user;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", foreignKey = @ForeignKey(name = "PRO_USR_FK"))
    private Project m_project;

    public User getUser() {
        return m_user;
    }

    public void setUser(final User p_user) {
        m_user = p_user;
    }

    public Project getProject() {
        return m_project;
    }

    public void setProject(final Project p_project) {
        m_project = p_project;
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
        if (!(p_obj instanceof UserProject)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final UserProject casted = (UserProject) p_obj;

        if (getId() != null) {
            final Long otherId = casted.getId();
            if (otherId != null) {
                builder.append(getId(), casted.getId());
                return builder.isEquals();
            }
        }

        builder.append(getUser(), casted.getUser());
        builder.append(getProject(), casted.getProject());

        return builder.isEquals();
    }

    @Override
    public final int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getUser());
        builder.append(getProject());

        return builder.toHashCode();
    }
}
