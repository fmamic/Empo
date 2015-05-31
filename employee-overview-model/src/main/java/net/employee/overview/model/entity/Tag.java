package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Audited
@Table(name = "EMP_TAG")
@SequenceGenerator(name = "TAG_SEQ", sequenceName = "TAG_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "TAG_VERSION"))
public class Tag extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAG_SEQ")
    @Column(name = "TAG_ID")
    private Long id;

    @Column(name = "TAG_NAME", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "TAG_TGT_ID", foreignKey = @ForeignKey(name = "TAG_TGT_FK"))
    private TagType tagType;

    @Column(name = "TAG_DISPLAY_NAME")
    private String displayName;

    @Column(name = "TAG_DESCRIPTION", columnDefinition = "text")
    private String description;

    @Column(name = "TAG_LINK")
    private String link;

    @OneToMany(mappedBy = "tag")
    private List<UserTag> users;

    @OneToMany(mappedBy = "tag")
    private List<BadgeTag> badges;

    @OneToMany(mappedBy = "tag")
    private List<ProjectTag> projects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType = tagType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String p_displayName) {
        displayName = p_displayName;
    }

    public List<UserTag> getUsers() {
        return users;
    }

    public void setUsers(final List<UserTag> p_users) {
        users = p_users;
    }

    public List<BadgeTag> getBadges() {
        return badges;
    }

    public void setBadges(final List<BadgeTag> p_badges) {
        badges = p_badges;
    }

    public List<ProjectTag> getProjects() {
        return projects;
    }

    public void setProjects(final List<ProjectTag> p_projects) {
        projects = p_projects;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tag)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final Tag casted = (Tag) obj;

        if(getId() != null) {
            final Long otherId = casted.getId();
            if(otherId != null) {
                builder.append(getId(), casted.getId());
                return builder.isEquals();
            }
        }

        builder.append(getName(), casted.getName());

        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getName());

        return builder.toHashCode();
    }
}
