package net.employee.overview.model.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.employee.overview.model.AbstractCode;

@Entity
@Table(name = "EMP_ROLE")
@SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ", allocationSize = 1)
public class Role extends AbstractCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    @Column(name = "ROLE_ID")
    private Long m_id;

    @Column(name = "ROLE_NAME")
    private String m_name;

    public final String getName() {
        return m_name;
    }

    public final void setName(final String p_name) {
        m_name = p_name;
    }

    public final Long getId() {
        return m_id;
    }

    public final void setId(final Long p_id) {
        m_id = p_id;
    }
}
