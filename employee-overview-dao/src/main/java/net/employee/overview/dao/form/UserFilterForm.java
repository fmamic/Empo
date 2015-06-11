package net.employee.overview.dao.form;

public class UserFilterForm {

    private Long m_id;

    private String m_name;

    public Long getId() {
        return m_id;
    }

    public void setId(final Long p_id) {
        m_id = p_id;
    }

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }
}
