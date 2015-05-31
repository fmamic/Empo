package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
import net.employee.overview.model.code.Role;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Audited
@Table(name = "EMP_USER")
@SequenceGenerator(name = "USR_SEQ", sequenceName = "USR_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "USR_VERSION"))
public class User extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USR_SEQ")
    @Column(name = "USR_ID")
    private Long id;

    @Column(name = "USR_USERNAME")
    private String username;

    @Column(name = "USR_PICTURE")
    private String picture;

    @Column(name = "USR_PASSWORD")
    private String password;

    @Column(name = "USR_NAME")
    private String name;

    @Column(name = "USR_POSITION")
    private String position;

    @Column(name = "USR_PHONE")
    private String phone;

    @Column(name = "USR_CELL_PHONE")
    private String cellPhone;

    @Column(name = "USR_DESCRIPTION", columnDefinition = "text")
    private String description;

    @Column(name = "USR_EMAIL")
    private String email;

    @Column(name = "USR_WORK_PLACE")
    private String workPlace;

    @Column(name = "USR_DATE_OF_BIRTH")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<UserBadge> badges;

    @OneToMany(mappedBy = "user")
    private List<UserProject> projects;

    @OneToMany(mappedBy = "user")
    private List<UserTag> tags;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne
    @JoinColumn(name = "USR_ROLE_ID", foreignKey = @ForeignKey(name = "USR_ROLE_FK"))
    private Role role;

    @ManyToOne
    @JoinColumn(name = "USR_MAN_ID", foreignKey = @ForeignKey(name = "USR_MAN_FK"))
    private User manager;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(final User p_manager) {
        manager = p_manager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String p_email) {
        email = p_email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(final String p_picture) {
        picture = p_picture;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(final String p_workPlace) {
        workPlace = p_workPlace;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(final String p_cellPhone) {
        cellPhone = p_cellPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(final String p_name) {
        name = p_name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final Date p_dateOfBirth) {
        dateOfBirth = p_dateOfBirth;
    }

    public List<UserBadge> getBadges() {
        return badges;
    }

    public void setBadges(final List<UserBadge> p_badges) {
        badges = p_badges;
    }

    public List<UserProject> getProjects() {
        return projects;
    }

    public void setProjects(final List<UserProject> p_projects) {
        projects = p_projects;
    }

    public List<UserTag> getTags() {
        return tags;
    }

    public void setTags(final List<UserTag> p_tags) {
        tags = p_tags;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final User casted = (User) obj;

        if (getId() != null) {
            final Long otherId = casted.getId();
            if (otherId != null) {
                builder.append(getId(), casted.getId());
                return builder.isEquals();
            }
        }

        builder.append(getName(), casted.getName());
        builder.append(getPhone(), casted.getPhone());
        builder.append(getEmail(), casted.getEmail());

        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getName());
        builder.append(getPhone());
        builder.append(getEmail());

        return builder.toHashCode();
    }
}
