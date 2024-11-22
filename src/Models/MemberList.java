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

    public ArrayList<Member> searchMembers(String name) {
        ArrayList<Member> searchResult = new ArrayList<>();
        for (Member member : members) {
            if (member.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResult.add(member);
            }
        }
        return searchResult;
    }
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
}
