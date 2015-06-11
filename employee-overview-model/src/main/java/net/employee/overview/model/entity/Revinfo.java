package net.employee.overview.model.entity;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@RevisionEntity
@Table(name = "REVINFO")
public class Revinfo {

    @Id
    @GeneratedValue
    @RevisionNumber
    private Integer m_rev;

    @RevisionTimestamp
    private Long m_revtstmp;

    public Integer getRev() {
        return m_rev;
    }

    public void setRev(final Integer p_rev) {
        m_rev = p_rev;
    }

    public Long getRevtstmp() {
        return m_revtstmp;
    }

    public void setRevtstmp(final Long p_revtstmp) {
        m_revtstmp = p_revtstmp;
    }
}
