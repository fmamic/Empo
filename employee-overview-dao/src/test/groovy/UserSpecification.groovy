import net.employee.overview.dao.UserRepository
import net.employee.overview.model.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional

import org.springframework.test.context.ContextConfiguration

import spock.lang.Specification

import spock.lang.Subject
import spock.lang.Unroll

@ContextConfiguration("classpath*:emp-dao-test-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
class UserSpecification extends Specification {

    @Autowired
    @Subject
    UserRepository userRepository;

    @Unroll
    def "Should bring context and save value to test DB"() {
        when:
        User user = new User();
        user.firstName = "FirstName2"
        user.lastName = "lastName2"

        then:
        userRepository.save(user)
        def userSaved = userRepository.findAll().last()

        expect:
        userSaved
    }
}
