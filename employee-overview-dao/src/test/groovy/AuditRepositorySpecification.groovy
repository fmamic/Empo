import net.employee.overview.dao.TagRepository
import net.employee.overview.model.entity.Tag

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional

import spock.lang.Specification

@ContextConfiguration("classpath*:emp-dao-test-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
class AuditRepositorySpecification extends Specification {

    @Autowired
    private TagRepository m_tagRepository;

    def "Should enter Aspect and save action info"() {
        when:
        Tag tag = new Tag( name: "TagName", description: "Description", displayName: "Display name", link: "Link");

        then:
        m_tagRepository.save(tag);

        expect:
        tag
    }

}
