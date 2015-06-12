package net.employee.overview.service.aspect;

import java.util.Date;

import net.employee.overview.model.AbstractPersistable;
import net.employee.overview.model.entity.ActionInfo;
import net.employee.overview.model.entity.UserBadge;
import net.employee.overview.service.ActionInfoService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ActionAspect {

    @Autowired
    private ActionInfoService m_actionInfoService;

    @SuppressWarnings("unchecked")
    @Before("execution(* net.employee.overview.service.*.save(..)) && args(p_type, p_object)")
    public final void saveActionInfo(final Class p_type, final AbstractPersistable p_object) {
        if (p_object instanceof UserBadge) {
            final ActionInfo actionInfo = new ActionInfo();

            actionInfo.setType(p_type.toString());
            actionInfo.setAction(((UserBadge) p_object).getUser().getName() + " ADD " + ((UserBadge) p_object).getBadge().getName());
            actionInfo.setEntityId(((UserBadge) p_object).getBadge().getId());
            actionInfo.setUserId(((UserBadge) p_object).getUser().getId());
            actionInfo.setTimestamp(new Date());
            actionInfo.setEntityName(((UserBadge) p_object).getBadge().getName());
            actionInfo.setUserName(((UserBadge) p_object).getUser().getName());

            m_actionInfoService.save(ActionInfo.class, actionInfo);
        }
    }

}
