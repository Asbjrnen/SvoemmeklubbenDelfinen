package Models;

import java.util.ArrayList;

public class MemberList {
    private ArrayList<Member> members = new ArrayList<>();

    //Tilføjer members
    public void addMember(Member member) {
        members.add(member);
    }

    //Fjerner members
    public void removeMember(String name) {
        members.remove(searchMembers(name).getFirst());
    }

    //Skal bruges i Controller til save-file
    public ArrayList<Member> getMembersList() {
        return members;
    }

    // SØGER EFTER MEDLEMMERS EFTERNAVN
    public ArrayList<Member> searchMembers(String name) {
        ArrayList<Member> searchResult = new ArrayList<>();
        for (Member member : members) {
            if (member.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResult.add(member);
            }
        }
        return searchResult;
    }

    // RETURNERER EN STRING MED ALLE MEDLEMMER
    public String printMembers() {
        String membersString = "";
        if (members.isEmpty()) {
            membersString = "No members have been added to the list";
        } else {
            for (Member member : members) {
                membersString += member + "\n ";
            }
        }
        return membersString;
    }

    // FINDER OG RETURNERER ET SPECIFIKT MEDLEM VIA NAVN
    public String findMember(String name) {
        String membersString = "";
        for (Member member : members) {
            if (member.getName().toLowerCase().contains(name.toLowerCase())) {
                membersString += member + "\n";
            }
        }
        return membersString;
    }
}
