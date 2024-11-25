package Models;

import Models.Comparators.AgeComparator;
import Models.Comparators.MemberTComparator;
import Data_source.FileHandler;
import Models.Comparators.NameComparator;
import Models.Comparators.SwimTComparator;

import java.util.ArrayList;

public class Controller {
    private MemberList memberList;
    private FileHandler fileHandler;

    public Controller(){
        memberList = new MemberList();
        fileHandler = new FileHandler();
    }

    public void readMemberList(){
        for (Member meber : fileHandler.readFileMemberList()){
            memberList.addMember(meber);
        }
    }

    public void saveMemberList(){
        fileHandler.saveFile(memberList.getMembersList());
    }

    public void addMember(Member member) {
        memberList.addMember(member);
        saveMemberList();
    }

    public void removeMember(String title) {
        memberList.removeMember(title);
        saveMemberList();
    }

    public String printMembers() {
        return memberList.printMembers();
    }

    public ArrayList<Member> searchMembers(String name) {
        return memberList.searchMembers(name);
    }

    public ArrayList<Member> getMembers() {
        return memberList.getMembersList();
    }

    public void sortByName(){
        NameComparator nameComparator = new NameComparator();
        getMembers().sort(nameComparator);
        saveMemberList();
    }

    public void sortByAge(){
        AgeComparator ageComparator = new AgeComparator();
        getMembers().sort(ageComparator);
    }

    public void sortByMemberType(){
        MemberTComparator memberTComparator = new MemberTComparator();
        getMembers().sort(memberTComparator);
    }

    public void sortBySwimType(){
        SwimTComparator swimTComparator = new SwimTComparator();
        getMembers().sort(swimTComparator);
    }

    public String findMember(String name) {
        return memberList.findMember(name);
    }

}