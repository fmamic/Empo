package net.employee.overview.web.form;

import java.util.List;

public class UserBadgeForm {

    private Long personId;

    private List<Long> badgesId;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(final Long p_personId) {
        personId = p_personId;
    }

    public List<Long> getBadgesId() {
        return badgesId;
    }

    public void setBadgesId(final List<Long> p_badgesId) {
        badgesId = p_badgesId;
    }
}
