package Models;

import java.util.ArrayList;

public class MemberList {
private ArrayList<Member> members = new ArrayList<>();

//Tilføjer members
public void addMember(Member member) {
    members.add(member);
}
//Fjerner members
public void removeMember(Member member) {
    members.remove(member);
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
}
