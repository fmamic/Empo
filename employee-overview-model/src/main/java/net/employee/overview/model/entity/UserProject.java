package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
import org.hibernate.envers.Audited;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Audited
@Table(name = "EMP_USR_PRO")
@SequenceGenerator(name = "USR_PRO_SEQ", sequenceName = "USR_PRO_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "PRO_VERSION"))
public class UserProject extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USR_PRO_SEQ")
    @Column(name = "USR_PRO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USR_ID", foreignKey = @ForeignKey(name = "USR_PRO_FK"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", foreignKey = @ForeignKey(name = "PRO_USR_FK"))
    private Project project;

    public User getUser() {
        return user;
    }

    public void setUser(final User p_user) {
        user = p_user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(final Project p_project) {
        project = p_project;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long p_id) {
        id = p_id;
    }
}
