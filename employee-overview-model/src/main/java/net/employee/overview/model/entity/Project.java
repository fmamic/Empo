package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Audited
@Table(name = "EMP_PROJECT")
@SequenceGenerator(name = "PRO_SEQ", sequenceName = "PRO_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "PRO_VERSION"))
public class Project extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_SEQ")
    @Column(name = "PRO_ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PRO_TAG", joinColumns = {
        @JoinColumn(name = "PRO_ID")}, inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    private List<Tag> projectTags;

    @Column(name = "PRO_DESCRIPTION")
    private String description;

    @Column(name = "PRO_NAME")
    private String name;

    @ManyToMany(mappedBy = "projects")
    private List<User> members;

    @Column(name = "PRO_FROM_DATE")
    private Date fromDate;

    @Column(name = "PRO_TO_DATE")
    private Date toDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Tag> getProjectTags() {
        return projectTags;
    }

    public void setProjectTags(final List<Tag> p_projectTags) {
        projectTags = p_projectTags;
    }
}
