package net.employee.overview.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "EMP_BADGE")
@SequenceGenerator(name = "BDG_SEQ", sequenceName = "BDG_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "BDG_VERSION"))
public class Badge extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BDG_SEQ")
    @Column(name = "BDG_ID")
    private Long m_id;

    @Column(name = "BDG_NAME")
    private String m_name;

    @ManyToOne
    @NotAudited
    @JoinColumn(name = "BDG_BDT_ID", foreignKey = @ForeignKey(name = "BDG_BDT_FK"))
    private BadgeType m_badgeType;

    @Column(name = "BDG_LEVEL")
    private Integer m_level;

    @Column(name = "BDG_DESCRIPTION", columnDefinition = "text")
    private String m_description;

    @ManyToOne
    @NotAudited
    @JoinColumn(name = "BDG_USR_ID", foreignKey = @ForeignKey(name = "BDG_USR_FK"))
    private User m_creator;

    @Column(name = "BDG_DATE")
    private Date m_date;

    @NotAudited
    @OneToMany(mappedBy = "m_badge")
    private List<BadgeTag> m_tags;

    @NotAudited
    @OneToMany(mappedBy = "m_badge")
    private List<UserBadge> m_badges;

    @Column(name = "BDG_IMAGE")
    private byte[] m_image;

    public BadgeType getBadgeType() {
        return m_badgeType;
    }

    public void setBadgeType(final BadgeType p_badgeType) {
        m_badgeType = p_badgeType;
    }

    public Integer getLevel() {
        return m_level;
    }

    public void setLevel(final Integer p_level) {
        m_level = p_level;
    }

    public String getDescription() {
        return m_description;
    }

    public void setDescription(final String p_description) {
        m_description = p_description;
    }

    public Date getDate() {
        return m_date;
    }

    public void setDate(final Date p_date) {
        m_date = p_date;
    }

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }

    public Long getId() {
        return m_id;
    }

    public void setId(final Long p_id) {
        m_id = p_id;
    }

    public User getCreator() {
        return m_creator;
    }

    public void setCreator(final User p_creator) {
        m_creator = p_creator;
    }

    public byte[] getImage() {
        return m_image.clone();
    }

    public void setImage(final byte[] p_image) {
        m_image = p_image.clone();
    }

    public List<UserBadge> getBadges() {
        return m_badges;
    }

    public void setBadges(final List<UserBadge> p_badges) {
        m_badges = p_badges;
    }

    public List<BadgeTag> getTags() {
        return m_tags;
    }

    public void setTags(final List<BadgeTag> p_tags) {
        m_tags = p_tags;
    }


    @Override
    public final boolean equals(final Object p_obj) {
        if (p_obj == this) {
            return true;
        }
        if (!(p_obj instanceof Badge)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final Badge casted = (Badge) p_obj;

        if (getId() != null) {
            final Long otherId = casted.getId();
            if (otherId != null) {
                builder.append(getId(), casted.getId());
                return builder.isEquals();
            }
        }

        builder.append(getName(), casted.getName());
        builder.append(getDate(), casted.getDate());

        return builder.isEquals();
    }

    @Override
    public final int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getName());
        builder.append(getDate());

        return builder.toHashCode();
    }
}
