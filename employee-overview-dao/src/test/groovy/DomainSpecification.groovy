import net.employee.overview.model.entity.Badge
import net.employee.overview.model.entity.BadgeTag
import spock.lang.Specification

class DomainSpecification extends Specification {

    def "Should test equals and hash code without id"() {
        when:
        Badge badge = new Badge(date: new Date(2011, 2, 2), name: "badge");
        Badge badge2 = new Badge(date: new Date(2011, 2, 2), name: "badge");

        Badge badge3 = new Badge(date: new Date(2011, 2, 2), name: "badge1");
        Badge badge4 = new Badge(date: new Date(2011, 2, 2), name: "badge2");

        Badge badge5 = new Badge(id: 1, date: new Date(2011, 2, 2), name: "badge2");
        Badge badge6 = new Badge(id: 1, date: new Date(2011, 2, 2), name: "badge");

        Badge badge7 = new Badge(id: 1, date: new Date(2011, 2, 2), name: "badge");
        Badge badge9 = new Badge(id: 2, date: new Date(2011, 2, 2), name: "badge");

        then:
        badge.equals(badge2);
        !badge3.equals(badge4);
        badge5.equals(badge6);
        !badge7.equals(badge9);
    }

    def "Should test equals and hash code with ID"() {
        when:
        Badge badge = new Badge(date: new Date(2011, 2, 2), name: "badge");
        Badge badge3 = new Badge(date: new Date(2011, 2, 2), name: "badge1");
        Badge badge6 = new Badge(id: 1, date: new Date(2011, 2, 2), name: "badge");
        Badge badge9 = new Badge(id: 2, date: new Date(2011, 2, 2), name: "badge");

        List<Badge> badges = [];

        badges.add(badge);
        badges.add(badge3);
        badges.add(badge6);
        badges.add(badge9);

        Badge badgeEquals = new Badge(id: 1);


        then:
        badges.contains(badgeEquals);
    }

    def "Should test equality of BadgeTag entity"() {
        when:
        Badge badge = new Badge(date: new Date(2011, 2, 2), name: "badge");

        BadgeTag badgeTag = new BadgeTag(badge: badge);

        then:
        badgeTag.equals(new BadgeTag(badge: badge))
    }

}
