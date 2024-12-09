import models.Member;
import models.MemberList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberListTest {

    @org.junit.jupiter.api.Test
    void addMember() {
        // OPRETTER NY LISTE MED ET NYT MEDLEM
        MemberList memberList = new MemberList();
        Member member = new Member("Test Name", 20, "Crawl", true, 1, true, false, "0:30", "1:00");

        // TILFØJER DET NYE MEDLEM TIL LISTEN
        memberList.addMember(member);

        // TJEKKER AT LISTEN KUN HAR DET ENE MEDLEM
        assertEquals(1, memberList.getMembersList().size());

        // TJEKKER AT DATAENE STEMMER OVERENS MED DET VI HAR SKREVET OVEN FOR I TESTEN
        assertEquals("Test Name", memberList.getMembersList().get(0).getName());
    }
}