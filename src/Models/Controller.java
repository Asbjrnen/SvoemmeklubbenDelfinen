package Models;

import Data_source.FileHandler;
import Models.Comparators.NameComparator;

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

    public String findMember(String name) {
        return memberList.findMember(name);
    }

}