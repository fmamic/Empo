package net.employee.overview.model.entity;

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
@Table(name = "EMP_TAG")
@SequenceGenerator(name = "TAG_SEQ", sequenceName = "TAG_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "TAG_VERSION"))
public class Tag extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAG_SEQ")
    @Column(name = "TAG_ID")
    private Long m_id;

    @Column(name = "TAG_NAME", unique = true)
    private String m_name;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "TAG_TGT_ID", foreignKey = @ForeignKey(name = "TAG_TGT_FK"))
    private TagType m_tagType;

    @Column(name = "TAG_DISPLAY_NAME")
    private String m_displayName;

    @Column(name = "TAG_DESCRIPTION", columnDefinition = "text")
    private String m_description;

    @Column(name = "TAG_LINK")
    private String m_link;

    @NotAudited
    @OneToMany(mappedBy = "m_tag")
    private List<UserTag> m_users;

    @NotAudited
    @OneToMany(mappedBy = "m_tag")
    private List<BadgeTag> m_badges;

    @NotAudited
    @OneToMany(mappedBy = "m_tag")
    private List<ProjectTag> m_projects;

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }

    public TagType getTagType() {
        return m_tagType;
    }

    public void setTagType(final TagType p_tagType) {
        m_tagType = p_tagType;
    }

    public String getDescription() {
        return m_description;
    }

    public void setDescription(final String p_description) {
        m_description = p_description;
    }

    public String getLink() {
        return m_link;
    }

    public void setLink(final String p_link) {
        m_link = p_link;
    }

    public Long getId() {
        return m_id;
    }

    public void setId(final Long p_id) {
        m_id = p_id;
    }

    public String getDisplayName() {
        return m_displayName;
    }

    public void setDisplayName(final String p_displayName) {
        m_displayName = p_displayName;
    }

    public List<UserTag> getUsers() {
        return m_users;
    }

    public void setUsers(final List<UserTag> p_users) {
        m_users = p_users;
    }

    public List<BadgeTag> getBadges() {
        return m_badges;
    }

    public void setBadges(final List<BadgeTag> p_badges) {
        m_badges = p_badges;
    }

    public List<ProjectTag> getProjects() {
        return m_projects;
    }

    public void setProjects(final List<ProjectTag> p_projects) {
        m_projects = p_projects;
    }

    @Override
    public final boolean equals(final Object p_obj) {
        if (p_obj == this) {
            return true;
        }
        if (!(p_obj instanceof Tag)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final Tag casted = (Tag) p_obj;

        if (getId() != null) {
            final Long otherId = casted.getId();
            if (otherId != null) {
                builder.append(getId(), casted.getId());
                return builder.isEquals();
            }
        }

        builder.append(getName(), casted.getName());

        return builder.isEquals();
    }

    @Override
    public final int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getName());

        return builder.toHashCode();
    }
}
