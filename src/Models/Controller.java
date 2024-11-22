package Models;

import java.util.ArrayList;

public class Controller {
    private MemberList memberList;

    public Controller(){
        memberList = new MemberList();
    }

    public void addMember(Member member) {
        memberList.addMember(member);
    }

    public void removeMember(String title) {
        memberList.removeMember(title);
    }

    public String printMembers() {
        return memberList.printMembers();
    }

    public ArrayList<Member> searchMembers(String name) {
        return memberList.searchMembers(name);
    }

}