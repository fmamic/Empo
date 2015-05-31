package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BDG_ID", foreignKey = @ForeignKey(name = "BDG_TAG_FK"))
    private Badge badge;

    @ManyToOne(cascade = CascadeType.ALL)
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

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BadgeTag)) {
            return false;
        }

        final BadgeTag casted = (BadgeTag) obj;
        final EqualsBuilder builder = new EqualsBuilder();

        builder.append(getTag(), casted.getTag());
        builder.append(getBadge(), casted.getBadge());

        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getBadge());
        builder.append(getTag());

        return builder.toHashCode();
    }
}
