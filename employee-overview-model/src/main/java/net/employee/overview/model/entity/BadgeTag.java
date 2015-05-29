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
@Table(name = "EMP_BDG_TAG")
@SequenceGenerator(name = "BDG_TAG_SEQ", sequenceName = "BDG_TAG_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "PRO_VERSION"))
public class BadgeTag extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BDG_TAG_SEQ")
    @Column(name = "BDG_TAG_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BDG_ID", foreignKey = @ForeignKey(name = "BDG_TAG_FK"))
    private Badge badge;

    @ManyToOne
    @JoinColumn(name = "TAG_ID", foreignKey = @ForeignKey(name = "TAG_BDG_FK"))
    private Tag tag;

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(final Badge p_badge) {
        badge = p_badge;
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
