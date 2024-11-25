package Models;

import Models.Comparators.*;
import Data_source.FileHandler;

import java.util.ArrayList;

public class Controller {
    private MemberList memberList;
    private FileHandler fileHandler;

    public Controller(){
        memberList = new MemberList();
        fileHandler = new FileHandler();
    }

    public void readMemberList(){
        for (Member member : fileHandler.readFileMemberList()){
            memberList.addMember(member);
        }
    }

    public void saveMemberList(){
        fileHandler.saveFile(memberList.getMembersList());
    }

    public ArrayList<UserLogIn> readLogInFile(){
        return fileHandler.readUserLogIn();
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
    public void sortByID(){
        IDComparator idComparator = new IDComparator();
        getMembers().sort(idComparator);
    }

    public String findMember(String name) {
        return memberList.findMember(name);
    }

    public boolean isIDTaken(int id) {
        for (Member member : memberList.getMembersList()) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }

}