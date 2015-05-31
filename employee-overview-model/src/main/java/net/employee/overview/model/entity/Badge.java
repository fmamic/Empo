package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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

    @ManyToOne
    @JoinColumn(name = "BDG_BDT_ID", foreignKey = @ForeignKey(name = "BDG_BDT_FK"))
    private BadgeType badgeType;

    @Column(name = "BDG_LEVEL")
    private Integer level;

    @Column(name = "BDG_DESCRIPTION", columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "BDG_USR_ID", foreignKey = @ForeignKey(name = "BDG_USR_FK"))
    private User creator;

    @Column(name = "BDG_DATE")
    private Date date;

    @OneToMany(mappedBy = "badge")
    private List<BadgeTag> tags;

    @OneToMany(mappedBy = "badge")
    private List<UserBadge> badges;

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
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Badge)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final Badge casted = (Badge) obj;

        if(getId() != null) {
            final Long otherId = casted.getId();
            if(otherId != null) {
                builder.append(getId(), casted.getId());
                return builder.isEquals();
            }
        }

        builder.append(getName(), casted.getName());
        builder.append(getDate(), casted.getDate());

        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getName());
        builder.append(getDate());

        return builder.toHashCode();
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(final byte[] p_image) {
        image = p_image;
    }

    public List<UserBadge> getBadges() {
        return badges;
    }

    public void setBadges(final List<UserBadge> p_badges) {
        badges = p_badges;
    }

    public List<BadgeTag> getTags() {
        return tags;
    }

    public void setTags(final List<BadgeTag> p_tags) {
        tags = p_tags;
    }
}
