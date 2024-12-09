package models;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemberListTest {

    @Test
    void addMember() {
        // OPRETTER NY LISTE MED ET NYT MEDLEM
        MemberList memberList = new MemberList();
        Member member = new Member("Test Name", 20, "Crawl", true, 1, true, false, "0:30", "1:00");

        // TILFØJER DET NYE MEDLEM TIL LISTEN
        memberList.addMember(member);

        // TJEKKER AT LISTEN KUN HAR DET ENE MEDLEM
        assertEquals(1, memberList.getMemberList().size());

        // TJEKKER AT DATAENE STEMMER OVERENS MED DET VI HAR SKREVET OVEN FOR I TESTEN
        assertEquals("Test Name", memberList.getMemberList().get(0).getName());
    }

    @Test
    void removeMember() {
        MemberList memberList = new MemberList();
        Member member = new Member("Test Name", 20, "Crawl", true, 1, true, false, "0:30", "1:00");
        memberList.addMember(member);
        memberList.removeMember("Test Name");
        assertEquals(0, memberList.getMemberList().size());
    }

    @Test
    void kontigentBeregner() {
        MemberList memberList = new MemberList();
        Member member1 = new Member("Test Name", 15, "Crawl", true, 1, true, true, "0:30", "1:00");
        Member member2 = new Member("Test Name", 20, "Crawl", true, 2, true, true, "0:30", "1:00");
        Member member3 = new Member("Test Name", 70, "Crawl", true, 3, true, true, "0:30", "1:00");
        Member member4 = new Member("Test Name", 50, "Crawl", false, 3, true, true, "0:30", "1:00");
        memberList.addMember(member1);
        memberList.addMember(member2);
        memberList.addMember(member3);
        memberList.addMember(member4);
        assertEquals(4300.0, memberList.kontingentBeregner());
    }
}