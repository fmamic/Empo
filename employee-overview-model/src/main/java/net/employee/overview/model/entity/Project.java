package net.employee.overview.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.employee.overview.model.AbstractPersistable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Audited
@Table(name = "EMP_PROJECT")
@SequenceGenerator(name = "PRO_SEQ", sequenceName = "PRO_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "PRO_VERSION"))
public class Project extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_SEQ")
    @Column(name = "PRO_ID")
    private Long m_id;

    @Column(name = "PRO_DESCRIPTION", columnDefinition = "text")
    private String m_description;

    @Column(name = "PRO_NAME")
    private String m_name;

    @Column(name = "PRO_FROM_DATE")
    private Date m_fromDate;

    @Column(name = "PRO_TO_DATE")
    private Date m_toDate;

    @NotAudited
    @OneToMany(mappedBy = "m_project")
    private List<ProjectTag> m_tags;

    @NotAudited
    @OneToMany(mappedBy = "m_project")
    private List<UserProject> m_users;

    public String getDescription() {
        return m_description;
    }

    public void setDescription(final String p_description) {
        m_description = p_description;
    }

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }

    public Date getFromDate() {
        return m_fromDate;
    }

    public void setFromDate(final Date p_fromDate) {
        m_fromDate = p_fromDate;
    }

    public Date getToDate() {
        return m_toDate;
    }

    public void setToDate(final Date p_toDate) {
        m_toDate = p_toDate;
    }

    public Long getId() {
        return m_id;
    }

    public void setId(final Long p_id) {
        m_id = p_id;
    }

    public List<ProjectTag> getTags() {
        return m_tags;
    }

    public void setTags(final List<ProjectTag> p_tags) {
        m_tags = p_tags;
    }

    public List<UserProject> getUsers() {
        return m_users;
    }

    public void setUsers(final List<UserProject> p_users) {
        m_users = p_users;
    }

    @Override
    public final boolean equals(final Object p_obj) {
        if (p_obj == this) {
            return true;
        }
        if (!(p_obj instanceof Project)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final Project casted = (Project) p_obj;

        if (getId() != null) {
            final Long otherId = casted.getId();
            if (otherId != null) {
                builder.append(getId(), casted.getId());
                return builder.isEquals();
            }
        }

        builder.append(getName(), casted.getName());
        builder.append(getFromDate(), casted.getFromDate());
        builder.append(getToDate(), casted.getToDate());

        return builder.isEquals();
    }

    @Override
    public final int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getName());
        builder.append(getFromDate());
        builder.append(getToDate());

        return builder.toHashCode();
    }
}
