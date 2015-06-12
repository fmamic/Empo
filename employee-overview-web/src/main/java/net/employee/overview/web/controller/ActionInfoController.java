package net.employee.overview.web.controller;

import java.util.List;

import net.employee.overview.model.entity.ActionInfo;
import net.employee.overview.service.ActionInfoService;

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
    public final @ResponseBody List<ActionInfo> searchAllActionInfo() {
        return m_actionInfoService.findAll(ActionInfo.class);
    }
}
