package Models;

import Models.Comparators.*;
import Data_source.FileHandler;

import java.util.ArrayList;

public class Controller {
    private MemberList memberList;
    private FileHandler fileHandler;


    // KONSTRUKTØR TIL INITIALISERING AF CONTROLLEREN
    public Controller(){
        memberList = new MemberList();
        fileHandler = new FileHandler();
    }

    // LÆSER MEDLEMMERNES LISTE FRA FIL
    public void readMemberList(){
        for (Member member : fileHandler.readFileMemberList()){
            memberList.addMember(member);
        }
    }

    // GEMMER MEDLEMSLISTEN TIL FIL
    public void saveMemberList(){
        fileHandler.saveFile(memberList.getMembersList());
    }

    // LÆSER LOGIN-OPLYSNINGERNE FRA FIL
    public ArrayList<UserLogIn> readLogInFile(){
        return fileHandler.readUserLogIn();
    }

    // TILFØJER NYT MEDLEM OG GEMMER LISTEN
    public void addMember(Member member) {
        memberList.addMember(member);
        saveMemberList();
    }

    // FJERNER ET MEDLEM FRA LISTEN
    public void removeMember(String title) {
        memberList.removeMember(title);
        saveMemberList();
    }

    // RETURNERER MEDLEMMER SOM STRING
    public String printMembers() {
        return memberList.printMembers();
    }

    // SØGER EFTER MEDLEMMER MED SPECIFIKT NAVN
    public ArrayList<Member> searchMembers(String name) {
        return memberList.searchMembers(name);
    }

    // GETTER FOR ALLE MEDLEMMER
    public ArrayList<Member> getMembers() {
        return memberList.getMembersList();
    }

    // SORTERER MEDLEMMER EFTER NAVN
    public void sortByName(){
        NameComparator nameComparator = new NameComparator();
        getMembers().sort(nameComparator);
        saveMemberList();
    }

    // SORTERER MEDLEMMER EFTER ALDER
    public void sortByAge(){
        AgeComparator ageComparator = new AgeComparator();
        getMembers().sort(ageComparator);
    }

    // SORTERER MEDLEMMER EFTER MEDLEMSTYPE (JUNIOR/SENIOR..)
    public void sortByMemberType(){
        MemberTComparator memberTComparator = new MemberTComparator();
        getMembers().sort(memberTComparator);
    }

    // SORTERER MEDLEMMER EFTER SVØMMEDISCIPLIN
    public void sortBySwimType(){
        SwimTComparator swimTComparator = new SwimTComparator();
        getMembers().sort(swimTComparator);
    }

    // SORTERER MEDLEMMER EFTER DERES ID
    public void sortByID(){
        IDComparator idComparator = new IDComparator();
        getMembers().sort(idComparator);
    }

    // FINDER ET SPECIFIKT MEDLEM VED NAVNET
    public String findMember(String name) {
        return memberList.findMember(name);
    }

    // TJEKKER OM ID ALLEREDE ER TAGET
    public boolean isIDTaken(int id) {
        for (Member member : memberList.getMembersList()) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public double kontigentBeregner(){
        return memberList.kontingentBeregner();
    }

    public String getRestance(){
        return memberList.getRestance();
    }

    public String printJuniorElites(){
        return memberList.printJuniorElites();
    }

    public String printSeniorElites(){
        return memberList.printSeniorELite();
    }

    public void addTrainingResult(String trainingResult){

    }

    public ArrayList<Member> searchEliteMembers(String name){
        return memberList.searchEliteMembers(name);
    }

    public MemberList getMemberList()
    {
        return memberList;
    }
}