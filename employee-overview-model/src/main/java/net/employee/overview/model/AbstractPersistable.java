package net.employee.overview.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractPersistable implements Identifier {

    @Version
    @Column(name = "VERSION")
    private Long m_version;

    public Long getVersion() {
        return m_version;
    }

    public void setVersion(final Long p_version) {
        m_version = p_version;
    }

}
