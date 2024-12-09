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
        assertEquals(1, memberList.getMembersList().size());

        // TJEKKER AT DATAENE STEMMER OVERENS MED DET VI HAR SKREVET OVEN FOR I TESTEN
        assertEquals("Test Name", memberList.getMembersList().get(0).getName());
    }

    @Test
    void removeMember() {
        MemberList memberList = new MemberList();
        Member member = new Member("Test Name", 20, "Crawl", true, 1, true, false, "0:30", "1:00");
        memberList.addMember(member);
        memberList.removeMember("Test Name");
        assertEquals(0, memberList.getMembersList().size());
        
    }
}