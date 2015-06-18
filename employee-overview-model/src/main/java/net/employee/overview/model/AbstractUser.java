package net.employee.overview.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractUser extends AbstractPersistable {

    @Column(name = "NAME")
    private String m_name;


    @Column(name = "USERNAME")
    private String m_username;

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }

    public String getUsername() {
        return m_username;
    }

    public void setUsername(final String p_username) {
        m_username = p_username;
    }
}
