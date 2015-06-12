package net.employee.overview.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.envers.ModifiedEntityNames;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity
@Table(name = "REVINFO")
public class Revinfo {

    @Id
    @GeneratedValue
    @RevisionNumber
    @Column(name = "REV")
    private Integer m_rev;

    @RevisionTimestamp
    @Column(name = "REVTSTMP")
    private Long m_revtstmp;

    @ElementCollection
    @JoinTable(name = "REVCHANGES", joinColumns = @JoinColumn(name = "REV"))
    @Column(name = "ENTITYNAME")
    @ModifiedEntityNames
    private Set<String> m_modifiedEntityNames;

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

    public Set<String> getModifiedEntityNames() {
        return m_modifiedEntityNames;
    }

    public void setModifiedEntityNames(final Set<String> p_modifiedEntityNames) {
        m_modifiedEntityNames = p_modifiedEntityNames;
    }
}
