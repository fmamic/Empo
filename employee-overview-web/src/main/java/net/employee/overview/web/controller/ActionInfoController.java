package net.employee.overview.web.controller;

import java.util.ArrayList;
import java.util.List;

import net.employee.overview.model.entity.ActionInfo;
import net.employee.overview.service.ActionInfoService;

import net.employee.overview.web.form.ActionInfoForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionInfoController {

    private final ActionInfoService m_actionInfoService;

    @Autowired
    public ActionInfoController(final ActionInfoService p_actionInfoService) {
        m_actionInfoService = p_actionInfoService;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/search/all/actionInfo")
    public final @ResponseBody List<ActionInfoForm> searchAllActionInfo() {
        final List<ActionInfoForm> actionInfoFormList = new ArrayList<ActionInfoForm>();

        final List<ActionInfo> actionInfos = m_actionInfoService.findAllWithSort();

        for (final ActionInfo actionInfo: actionInfos) {
            final ActionInfoForm actionInfoForm = new ActionInfoForm();
            BeanUtils.copyProperties(actionInfo, actionInfoForm);

            actionInfoFormList.add(actionInfoForm);
        }

        return actionInfoFormList;
    }
}
