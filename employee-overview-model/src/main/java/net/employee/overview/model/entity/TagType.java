package net.employee.overview.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.employee.overview.model.AbstractPersistable;

@Entity
@Table(name = "EMP_TAG_TYPE")
@SequenceGenerator(name = "TGT_SEQ", sequenceName = "TGT_SEQ", allocationSize = 1)
@AttributeOverride(name = "m_version", column = @Column(name = "TGT_VERSION"))
public class TagType extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TGT_SEQ")
    @Column(name = "TGT_ID")
    private Long m_id;

    @Column(name = "TGT_NAME")
    private String m_name;

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }

    public Long getId() {
        return m_id;
    }

    public void setId(final Long p_id) {
        m_id = p_id;
    }
}
