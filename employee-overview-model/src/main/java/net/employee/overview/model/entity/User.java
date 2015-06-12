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
import net.employee.overview.model.code.Role;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "EMP_USER")
@SequenceGenerator(name = "USR_SEQ", sequenceName = "USR_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "USR_VERSION"))
public class User extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USR_SEQ")
    @Column(name = "USR_ID")
    private Long m_id;

    @Column(name = "USR_USERNAME")
    private String m_username;

    @Column(name = "USR_PICTURE")
    private String m_picture;

    @Column(name = "USR_PASSWORD")
    private String m_password;

    @Column(name = "USR_NAME")
    private String m_name;

    @Column(name = "USR_POSITION")
    private String m_position;

    @Column(name = "USR_PHONE")
    private String m_phone;

    @Column(name = "USR_CELL_PHONE")
    private String m_cellPhone;

    @Column(name = "USR_DESCRIPTION", columnDefinition = "text")
    private String m_description;

    @Column(name = "USR_EMAIL")
    private String m_email;

    @Column(name = "USR_WORK_PLACE")
    private String m_workPlace;

    @Column(name = "USR_DATE_OF_BIRTH")
    private Date m_dateOfBirth;

    @OneToMany(mappedBy = "m_user")
    private List<UserBadge> m_badges;

    @OneToMany(mappedBy = "m_user")
    private List<UserProject> m_projects;

    @OneToMany(mappedBy = "m_user")
    private List<UserTag> m_tags;

    @ManyToOne
    @JoinColumn(name = "USR_ROLE_ID", foreignKey = @ForeignKey(name = "USR_ROLE_FK"))
    private Role m_role;

    @ManyToOne
    @JoinColumn(name = "USR_MAN_ID", foreignKey = @ForeignKey(name = "USR_MAN_FK"))
    private User m_manager;

    public String getPosition() {
        return m_position;
    }

    public void setPosition(final String p_position) {
        m_position = p_position;
    }

    public String getPhone() {
        return m_phone;
    }

    public void setPhone(final String p_phone) {
        m_phone = p_phone;
    }

    public String getUsername() {
        return m_username;
    }

    public void setUsername(final String p_username) {
        m_username = p_username;
    }

    public String getPassword() {
        return m_password;
    }

    public void setPassword(final String p_password) {
        m_password = p_password;
    }

    public Long getId() {
        return m_id;
    }

    public void setId(final Long p_id) {
        m_id = p_id;
    }

    public String getDescription() {
        return m_description;
    }

    public void setDescription(final String p_description) {
        m_description = p_description;
    }

    public Role getRole() {
        return m_role;
    }

    public void setRole(final Role p_role) {
        m_role = p_role;
    }

    public User getManager() {
        return m_manager;
    }

    public void setManager(final User p_manager) {
        m_manager = p_manager;
    }

    public String getEmail() {
        return m_email;
    }

    public void setEmail(final String p_email) {
        m_email = p_email;
    }

    public String getPicture() {
        return m_picture;
    }

    public void setPicture(final String p_picture) {
        m_picture = p_picture;
    }

    public String getWorkPlace() {
        return m_workPlace;
    }

    public void setWorkPlace(final String p_workPlace) {
        m_workPlace = p_workPlace;
    }

    public String getCellPhone() {
        return m_cellPhone;
    }

    public void setCellPhone(final String p_cellPhone) {
        m_cellPhone = p_cellPhone;
    }

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }

    public Date getDateOfBirth() {
        return m_dateOfBirth;
    }

    public void setDateOfBirth(final Date p_dateOfBirth) {
        m_dateOfBirth = p_dateOfBirth;
    }

    public List<UserBadge> getBadges() {
        return m_badges;
    }

    public void setBadges(final List<UserBadge> p_badges) {
        m_badges = p_badges;
    }

    public List<UserProject> getProjects() {
        return m_projects;
    }

    public void setProjects(final List<UserProject> p_projects) {
        m_projects = p_projects;
    }

    public List<UserTag> getTags() {
        return m_tags;
    }

    public void setTags(final List<UserTag> p_tags) {
        m_tags = p_tags;
    }

    @Override
    public final boolean equals(final Object p_obj) {
        if (p_obj == this) {
            return true;
        }
        if (!(p_obj instanceof User)) {
            return false;
        }

        final EqualsBuilder builder = new EqualsBuilder();
        final User casted = (User) p_obj;

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
    public final int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getName());
        builder.append(getPhone());
        builder.append(getEmail());

        return builder.toHashCode();
    }
}
