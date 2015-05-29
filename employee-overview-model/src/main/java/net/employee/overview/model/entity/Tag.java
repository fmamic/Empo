package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Tag m_tag = (Tag) o;

        if (id != null ? !id.equals(m_tag.id) : m_tag.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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
}
