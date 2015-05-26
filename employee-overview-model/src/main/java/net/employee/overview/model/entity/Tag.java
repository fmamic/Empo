package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;

import javax.persistence.*;
import java.util.List;

@Entity
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

    @Column(name = "TAG_DESCRIPTION")
    private String description;

    @Column(name = "TAG_LINK")
    private String link;

    @ManyToMany(mappedBy = "tags")
    private List<User> users;

    @ManyToMany(mappedBy = "tags")
    private List<Badge> badges;

    @ManyToMany(mappedBy = "projectTags")
    private List<Project> projects;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> p_users) {
        users = p_users;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String p_displayName) {
        displayName = p_displayName;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(final List<Badge> p_badges) {
        badges = p_badges;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(final List<Project> p_projects) {
        projects = p_projects;
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
}
