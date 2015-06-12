import net.employee.overview.model.entity.ActionInfo
import net.employee.overview.model.entity.UserBadge
import net.employee.overview.service.ActionInfoService
import net.employee.overview.service.BadgeService
import net.employee.overview.service.UserBadgeService
import net.employee.overview.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import net.employee.overview.model.entity.User
import net.employee.overview.model.entity.Badge

@ContextConfiguration("classpath*:emp-service-test-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
class ActionInfoSpecification extends Specification {

    @Autowired
    UserBadgeService m_userBadgeService;

    @Autowired
    ActionInfoService m_actionInfoService;

    @Autowired
    UserService m_userService;

    @Autowired
    BadgeService m_badgeService;

    def "Should save Action info with Aspect"() {
        when:
        User user = new User(name: "Name", cellPhone: "sdes");
        Badge badge = new Badge(name: "Badge");

        User savedUser = m_userService.save(User.class, user);
        Badge savedBadge = m_badgeService.save(Badge.class, badge);

        UserBadge userBadge = new UserBadge(user: savedUser, badge: savedBadge);

        then:
        m_userBadgeService.save(UserBadge.class, userBadge);
        List<ActionInfo> actionInfos = m_actionInfoService.findAll(ActionInfo.class);

        expect:
        actionInfos
    }

}
