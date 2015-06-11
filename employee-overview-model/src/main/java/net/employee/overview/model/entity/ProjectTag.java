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

@Entity
@Audited
@Table(name = "EMP_PRO_TAG")
@SequenceGenerator(name = "PRO_TAG_SEQ", sequenceName = "PRO_TAG_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "PRO_VERSION"))
public class ProjectTag extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_TAG_SEQ")
    @Column(name = "PRO_TAG_ID")
    private Long m_id;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", foreignKey = @ForeignKey(name = "PRO_TAG_FK"))
    private Project m_project;

    @ManyToOne
    @JoinColumn(name = "TAG_ID", foreignKey = @ForeignKey(name = "TAG_PRO_FK"))
    private Tag m_tag;

    public Project getProject() {
        return m_project;
    }

    public void setProject(final Project p_project) {
        m_project = p_project;
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
        if (!(p_obj instanceof ProjectTag)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final ProjectTag casted = (ProjectTag) p_obj;

        if (getId() != null) {
            final Long otherId = casted.getId();
            if (otherId != null) {
                builder.append(getId(), casted.getId());
                return builder.isEquals();
            }
        }

        builder.append(getProject(), casted.getProject());
        builder.append(getTag(), casted.getTag());

        return builder.isEquals();
    }

    @Override
    public final int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getProject());
        builder.append(getTag());

        return builder.toHashCode();
    }
}
