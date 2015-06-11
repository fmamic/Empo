package net.employee.overview.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
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

import net.employee.overview.model.AbstractPersistable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "EMP_BDG_TAG")
@SequenceGenerator(name = "BDG_TAG_SEQ", sequenceName = "BDG_TAG_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "PRO_VERSION"))
public class BadgeTag extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BDG_TAG_SEQ")
    @Column(name = "BDG_TAG_ID")
    private Long m_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BDG_ID", foreignKey = @ForeignKey(name = "BDG_TAG_FK"))
    private Badge m_badge;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TAG_ID", foreignKey = @ForeignKey(name = "TAG_BDG_FK"))
    private Tag m_tag;

    public Badge getBadge() {
        return m_badge;
    }

    public void setBadge(final Badge p_badge) {
        m_badge = p_badge;
    }

    public Tag getTag() {
        return m_tag;
    }

    public void setTag(final Tag p_tag) {
        m_tag = p_tag;
    }

    public Long getId() {
        return m_id;
    }

    public void setId(final Long p_id) {
        m_id = p_id;
    }

    @Override
    public final boolean equals(final Object p_obj) {
        if (p_obj == this) {
            return true;
        }
        if (!(p_obj instanceof BadgeTag)) {
            return false;
        }

        final BadgeTag casted = (BadgeTag) p_obj;
        final EqualsBuilder builder = new EqualsBuilder();

        builder.append(getTag(), casted.getTag());
        builder.append(getBadge(), casted.getBadge());

        return builder.isEquals();
    }

    @Override
    public final int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getBadge());
        builder.append(getTag());

        return builder.toHashCode();
    }
}
