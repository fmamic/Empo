package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
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
}
