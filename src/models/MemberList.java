package models;

import java.util.ArrayList;
import java.util.Comparator;

public class MemberList {
    private static ArrayList<Member> members = new ArrayList<>();

    //Tilføjer members
    public static void addMember(Member member) {
        members.add(member);
    }

    //Fjerner members
    public void removeMember(String name) {
        members.remove(searchMembers(name).getFirst());
    }

    //Skal bruges i Controller til save-file
    public ArrayList<Member> getMemberList() {
        return members;
    }

    // SØGER EFTER MEDLEMMERS
    public ArrayList<Member> searchMembers(String name) {
        ArrayList<Member> searchResult = new ArrayList<>();
        for (Member member : members) {
            if (member.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResult.add(member);
            }
        }
        return searchResult;
    }

    public ArrayList<Member> searchEliteMembers(String name) {
        ArrayList<Member> searchResult = new ArrayList<>();
        for (Member member : getJuniorElites()) {
            if (member.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResult.add(member);
            }
        }
        for (Member member : getSeniorElites()) {
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

    // regner total kontigent ud for svømmeklubben
    public double kontingentBeregner() {
        double totalKontigent = 0.0;

        for (Member member : members) {
            totalKontigent += member.getKontigent();
        }
        return totalKontigent;
    }

    public String getRestance() {
        String printString = "Medlemmer i restance: \n";
        for (Member member : members) {
            printString += member + ", restance: " + member.getKontigent() + " kr.\n";
        }
        return printString;
    }

    public ArrayList<Member> getJuniorElites() {
        ArrayList<Member> juniorElites = new ArrayList<>();
        for (Member member : members) {
            if (member.getAge() < 18) {
                if (!member.isMotKon()) {
                    juniorElites.add(member);
                }
            }
        }
        return juniorElites;
    }

    public ArrayList<Member> getTop5Swimmers(String swimType, boolean isJunior) {
        ArrayList<Member> filteredSwimmers = new ArrayList<>();

        for (Member member : members) {
            boolean matchesAgeGroup = isJunior ? (member.getAge() < 18) : (member.getAge() >= 18);
            if (member.getSwimType().equalsIgnoreCase(swimType) && matchesAgeGroup) {
                filteredSwimmers.add(member);
            }
        }
        filteredSwimmers.sort(Comparator.comparing(Member::getBestTrainingResult));

        return new ArrayList<>(filteredSwimmers.subList(0, Math.min(5, filteredSwimmers.size())));
    }

    public String printJuniorElites() {
        String membersString = "";
        for (Member member : getJuniorElites()) {
            membersString += member + "\n";
        }
        return membersString;
    }

    public String printSeniorELite() {
        String membersString = "";
        for (Member member : getSeniorElites()) {
            membersString += member + "\n";
        }
        return membersString;
    }

    public ArrayList<Member> getSeniorElites() {
        ArrayList<Member> seniorElites = new ArrayList<>();
        for (Member member : members) {
            if (member.getAge() > 18) {
                if (!member.isMotKon()) {
                    seniorElites.add(member);
                }
            }
        }
        return seniorElites;
    }

}