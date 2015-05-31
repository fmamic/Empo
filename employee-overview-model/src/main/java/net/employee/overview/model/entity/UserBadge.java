package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(name = "EMP_USR_BDG")
@SequenceGenerator(name = "USR_BDG_SEQ", sequenceName = "USR_BDG_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "PRO_VERSION"))
public class UserBadge extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USR_BDG_SEQ")
    @Column(name = "USR_BDG_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USR_ID", foreignKey = @ForeignKey(name = "USR_BDG_FK"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "BDG_ID", foreignKey = @ForeignKey(name = "BDG_USR_FK"))
    private Badge badge;

    public User getUser() {
        return user;
    }

    public void setUser(final User p_user) {
        user = p_user;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(final Badge p_badge) {
        badge = p_badge;
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
        if (!(obj instanceof UserBadge)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final UserBadge casted = (UserBadge) obj;

        if (getId() != null) {
            final Long otherId = casted.getId();
            if (otherId != null) {
                builder.append(getId(), casted.getId());
                return builder.isEquals();
            }
        }

        builder.append(getUser(), casted.getUser());
        builder.append(getBadge(), casted.getBadge());

        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getBadge());
        builder.append(getUser());

        return builder.toHashCode();
    }
}
