package net.employee.overview.service.impl;

import java.util.List;

import net.employee.overview.dao.ActionInfoRepository;
import net.employee.overview.dao.BadgeRepository;
import net.employee.overview.dao.ProjectRepository;
import net.employee.overview.dao.TagRepository;
import net.employee.overview.dao.UserRepository;
import net.employee.overview.model.entity.ActionInfo;
import net.employee.overview.service.AbstractPersistableService;
import net.employee.overview.service.ActionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionInfoServiceImpl extends AbstractPersistableService implements ActionInfoService {

    @Autowired
    public ActionInfoServiceImpl(final BadgeRepository p_badgeRepository, final ProjectRepository p_projectRepository,
            final TagRepository p_tagRepository, final UserRepository p_userRepository,
            final ActionInfoRepository p_actionInfoRepository) {
        super(p_badgeRepository, p_projectRepository, p_tagRepository, p_userRepository, p_actionInfoRepository);
    }

    @SuppressWarnings("unchecked")
    public final List<ActionInfo> findAllWithSort() {
        return ((ActionInfoRepository) getRepository(ActionInfo.class)).findAllWithDateSort();
    }
}
