// IMPORTERER DE KLASSER, SOM JEG SKAL BRUGE TIL TESTEN, SOM ER I VORES SRC FOLDER. VI HENTER DEM FRA MODELS-PAKKEN I VORES PROJEKT
import models.Member;
import models.MemberList;
// ORG.JUNIT.JUPITER ER ET HENTET BIBLIOTEK TIL TESTS, SOM SPECIFICERER DET ER VERSION 5 A JUnit VI BRUGER
// API (Application Programming Interface) INDEHOLDER FUNKTIONER TIL TESTEN
import org.junit.jupiter.api.Test;
// IMPORT STATIC = BRUGER METODERNE DIREKTE, HVIS VI ALLEREDE HAR NOGLE EKSISTERENDE TING SOM FX ASSERTEQUALS
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberListTest
{
    @Test
    void addMemberTest()
    {
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