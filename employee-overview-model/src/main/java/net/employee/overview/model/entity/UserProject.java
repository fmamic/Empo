package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

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

@Entity
@Audited
@Table(name = "EMP_USR_PRO")
@SequenceGenerator(name = "USR_PRO_SEQ", sequenceName = "USR_PRO_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "PRO_VERSION"))
public class UserProject extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USR_PRO_SEQ")
    @Column(name = "USR_PRO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USR_ID", foreignKey = @ForeignKey(name = "USR_PRO_FK"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", foreignKey = @ForeignKey(name = "PRO_USR_FK"))
    private Project project;

    public User getUser() {
        return user;
    }

    public void setUser(final User p_user) {
        user = p_user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(final Project p_project) {
        project = p_project;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long p_id) {
        id = p_id;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UserProject)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final UserProject casted = (UserProject) obj;

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
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getUser());
        builder.append(getProject());

        return builder.toHashCode();
    }
}
