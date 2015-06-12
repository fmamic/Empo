import net.employee.overview.dao.AuditCriteria
import net.employee.overview.dao.AuditRepository
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
class AuditSpecification extends Specification {

    @Autowired
    private AuditRepository m_auditRepository;

    @Autowired
    private TagRepository m_tagRepository;

    @Autowired
    private AuditCriteria m_auditCriteria;

//    def "Should bring context and read values from audit table"() {
//        when:
//        Tag tag = new Tag( name: "TagName", description: "Description", displayName: "Display name", link: "Link");
//        m_tagRepository.save(tag);
//
//        then:
//        List<Object[]> auditTags = m_auditRepository.entityRevisions(Tag.class);
//
//        expect:
//        auditTags
//    }
//
//    def "Should read from RevInfo audit criteria"() {
//        when:
//        Tag tag = new Tag( name: "TagName", description: "Description", displayName: "Display name", link: "Link");
//        m_tagRepository.save(tag);
//
//        then:
//        List<Revinfo> revinfoList = m_auditCriteria.fetchEventsByDate();
//
//        expect:
//        revinfoList
//    }

    def "Should read from RevInfo and order entites by date"() {
        when:
        Tag tag = new Tag( name: "TagName", description: "Description", displayName: "Display name", link: "Link");
        m_tagRepository.save(tag);

        then:
        List<Object> revisions = m_auditRepository.entityRevisions();

        expect:
        revisions
    }

    def "Should enter Aspect and save action info"() {
        when:
        Tag tag = new Tag( name: "TagName", description: "Description", displayName: "Display name", link: "Link");

        then:
        m_tagRepository.save(tag);

        expect:
        tag
    }

}
