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
@Table(name = "EMP_PRO_TAG")
@SequenceGenerator(name = "PRO_TAG_SEQ", sequenceName = "PRO_TAG_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "PRO_VERSION"))
public class ProjectTag extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_TAG_SEQ")
    @Column(name = "PRO_TAG_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", foreignKey = @ForeignKey(name = "PRO_TAG_FK"))
    private Project project;

    @ManyToOne
    @JoinColumn(name = "TAG_ID", foreignKey = @ForeignKey(name = "TAG_PRO_FK"))
    private Tag tag;

    public Project getProject() {
        return project;
    }

    public void setProject(final Project p_project) {
        project = p_project;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(final Tag p_tag) {
        tag = p_tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long p_id) {
        id = p_id;
    }
}
