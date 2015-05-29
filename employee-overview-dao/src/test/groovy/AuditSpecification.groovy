import net.employee.overview.dao.TagRepository
import net.employee.overview.model.entity.Tag
import org.hibernate.envers.AuditReader
import org.hibernate.envers.AuditReaderFactory
import org.hibernate.envers.query.AuditQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@ContextConfiguration("classpath*:emp-dao-test-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
class AuditSpecification extends Specification {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TagRepository tagRepository;

    def "Should bring context and read values from audit table"() {
        when:
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        Tag tag = new Tag( name: "TagName", description: "Description", displayName: "Display name", link: "Link");
        Tag savedTag = tagRepository.save(tag);

        then:
        Tag tagAud = auditReader.find(Tag.class, savedTag.getId(), 1);

        AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(Tag.class, false, true);

        List<Tag> listTags = auditQuery.getResultList();

        expect:
        listTags
    }

}
