import net.employee.overview.model.entity.User
import net.employee.overview.service.AuditService
import net.employee.overview.service.UserService
import net.employee.overview.web.controller.UserController
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebAppConfiguration
class UserControllerSpecification extends Specification {

    def UserService userService = Mock(UserService)
    def AuditService auditService = Mock(AuditService)

    UserController userController = new UserController(userService, auditService);

    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build()
    }


    def "Should test getting user from UserController by username"() {
        setup:
        userService.fetchUserByUsername(_) >> new User(name: "user", username: "fmamic")

        when:
        def response = mockMvc.perform(get("/search/user/username/fmamic")).andReturn()

        then:
        def user = response.getResponse().getContentAsString()

        expect:
        user.contains("fmamic")
    }

}
