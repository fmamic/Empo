package net.employee.overview.web.form;

import java.util.List;

public class UserTagForm {

    private Long personId;

    private List<Long> tagsId;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(final Long p_personId) {
        personId = p_personId;
    }

    public List<Long> getTagsId() {
        return tagsId;
    }

    public void setTagsId(final List<Long> p_tagsId) {
        tagsId = p_tagsId;
    }
}
