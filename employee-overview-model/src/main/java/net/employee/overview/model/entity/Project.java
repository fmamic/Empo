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

    @Column(name = "PRO_DESCRIPTION", columnDefinition = "text")
    private String description;

    @Column(name = "PRO_NAME")
    private String name;

    @Column(name = "PRO_FROM_DATE")
    private Date fromDate;

    @Column(name = "PRO_TO_DATE")
    private Date toDate;

    @OneToMany(mappedBy = "project")
    private List<ProjectTag> tags;

    @OneToMany(mappedBy = "project")
    private List<UserProject> users;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProjectTag> getTags() {
        return tags;
    }

    public void setTags(final List<ProjectTag> p_tags) {
        tags = p_tags;
    }

    public List<UserProject> getUsers() {
        return users;
    }

    public void setUsers(final List<UserProject> p_users) {
        users = p_users;
    }
}
