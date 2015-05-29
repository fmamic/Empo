package net.employee.overview.web.form;

import java.util.Date;

public class RevisionForm<T extends Revision> {

    private T form;

    private String revisionType;

    private Date revisionDate;

    private String type;

    public T getForm() {
        return form;
    }

    public void setForm(final T p_form) {
        form = p_form;
    }

    public String getRevisionType() {
        return revisionType;
    }

    public void setRevisionType(final String p_revisionType) {
        revisionType = p_revisionType;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(final Date p_revisionDate) {
        revisionDate = p_revisionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(final String p_type) {
        type = p_type;
    }
}
