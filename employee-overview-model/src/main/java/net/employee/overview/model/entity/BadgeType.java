package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
import net.employee.overview.model.entity.User;
import net.employee.overview.model.enums.BadgePermission;

import javax.persistence.*;

@Entity
@Table(name = "EMP_BADGE_TYPE")
@SequenceGenerator(name = "BDT_SEQ", sequenceName = "BDT_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "BDT_VERSION"))
public class BadgeType extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BDT_SEQ")
    @Column(name = "BDT_ID")
    private Long id;

    @Column(name = "BDT_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "BDT_USR_ID", foreignKey = @ForeignKey(name = "BDT_USR_FK"))
    private User owner;

    @Column(name = "BDT_PERMISSION")
    private BadgePermission badgePermission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BadgePermission getBadgePermission() {
        return badgePermission;
    }

    public void setBadgePermission(final BadgePermission p_badgePermission) {
        badgePermission = p_badgePermission;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(final User p_owner) {
        owner = p_owner;
    }
}
