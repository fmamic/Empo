package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Audited
@Table(name = "EMP_BADGE")
@SequenceGenerator(name = "BDG_SEQ", sequenceName = "BDG_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "BDG_VERSION"))
public class Badge extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BDG_SEQ")
    @Column(name = "BDG_ID")
    private Long id;

    @Column(name = "BDG_NAME")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BDG_TAG", joinColumns = {
        @JoinColumn(name = "USR_ID") }, inverseJoinColumns = { @JoinColumn(name = "BDG_ID") })
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "BDG_BDT_ID", foreignKey = @ForeignKey(name = "BDG_BDT_FK"))
    private BadgeType badgeType;

    @Column(name = "BDG_LEVEL")
    private Integer level;

    @Column(name = "BDG_DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "BDG_USR_ID", foreignKey = @ForeignKey(name = "BDG_USR_FK"))
    private User creator;

    @Column(name = "BDG_DATE")
    private Date date;

    @ManyToMany(mappedBy = "badges")
    private List<User> users;

    @Column(name = "BDG_IMAGE")
    private byte[] image;

    public BadgeType getBadgeType() {
        return badgeType;
    }

    public void setBadgeType(BadgeType badgeType) {
        this.badgeType = badgeType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(final User p_creator) {
        creator = p_creator;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Badge m_badge = (Badge) o;

        if (id != null ? !id.equals(m_badge.id) : m_badge.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(final byte[] p_image) {
        image = p_image;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
