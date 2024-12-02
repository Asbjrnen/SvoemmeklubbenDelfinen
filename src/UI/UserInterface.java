package UI;

import Models.Controller;
import Models.Member;
import Models.UserLogIn;

import java.time.LocalDate;
import java.util.*;

public class UserInterface {
    private Scanner scanner;
    private Controller controller;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.controller = new Controller();
    }

    //STARTER PROGRAMMET
    public void Startprogram() {

        controller.readMemberList();
        //REGISTER LISTEN TIL UDVIKLER TEAMET (AL INFORMATION ER TILGÆNGELIGT)
        boolean exit = false;
        while (!exit) {
            System.out.println("-------------------------------------------------");
            System.out.println("1) Log in as formand");
            System.out.println("2) Log in as kasserer");
            System.out.println("3) Log in as træner");
            System.out.println("4) Log in as forældre");
            System.out.println("5) Exit");
            System.out.println("-------------------------------------------------");


            int choice = getIntInput("Choose an option:");
            switch (choice) {
                case 1 -> formandUserInterface();
                case 2 -> kassererUserInterface();
                case 3 -> trænerUserInterface();
                //case 4 -> forældreUserInterface();
                case 5 -> exit = true;
            }
        }

        boolean running = false;
        while (!running) {
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            if (checkLogIn(username, password) == true) {
                running = true;
            }
        }
    }


    //RESPONDERER TIL USERS INPUT OM DET ER KORREKT ELLER EJ
    private int getIntInput(String userInput) {
        while (true) {
            System.out.print(userInput);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    //TILFØJER ET MEMBER TIL MEMBERLIST.TXT
    private void addMember() {
        scanner.nextLine();
        System.out.println("Please enter the name of the member you would like to add: ");
        String name = scanner.nextLine();

        String swimType = "";
        System.out.println("Please enter the type of swimmer the member you would like to add is: ");
        boolean exit = false;
        while (!exit) {
            System.out.println("-------------------------------------------------");
            System.out.println("1) Crawl");
            System.out.println("2) Rygcrawl");
            System.out.println("3) Bryst");
            System.out.println("4) Butterfly");
            System.out.println("-------------------------------------------------");

            int choice = getIntInput("Choose an option:");
            switch (choice) {
                case 1 -> {
                    swimType = "crawl";
                    exit = true;
                }
                case 2 -> {
                    swimType = "rygcrawl";
                    exit = true;
                }
                case 3 -> {
                    swimType = "bryst";
                    exit = true;
                }
                case 4 -> {
                    swimType = "butterfly";
                    exit = true;
                }
            }
        }

        boolean memType = getIntInput("Please enter the type of member you would like to add: \"\\n 1 is for aktiv, 2 is for passiv ") == 1;
        if (memType) {
            System.out.println("Aktiv");
        } else {
            System.out.println("Passiv");
        }

        int age = getIntInput("Please enter the age of the member you would like to add: ");

        System.out.println("A numerical ID will now be generated for the member");
        int id = generateUniqueID();

        boolean motKon = getIntInput("Please enter the activity form your member is using: \"\\n 1 for motionist or 0 for konkurrencesvømmer\"") == 1;
        if (motKon) {
            System.out.println("Motionist");
        } else {
            System.out.println("Konkurrencesvømmer");
        }

        boolean isRes = getIntInput("Please enter whether the member is in restance or not: \"\\n 1 for yes or 2 for no\"") == 1;
        if (isRes) {
            System.out.println("Er i restance");
        } else {
            System.out.println("Er ikke i restance");
        }

        System.out.println("MemberID: " + id);
        controller.addMember(new Member(name, age, swimType, memType, id, motKon, isRes));
    }

    //GENERER ET RANDOM ID TIL ET OPRETTET MEMBER
    private int generateUniqueID() {
        Random rand = new Random();
        int max = 99999999, min = 10000000;
        int id;
        while (true) {
            id = rand.nextInt(max - min + 1) + min;
            if (!controller.isIDTaken(id)) {
                break;
            } else {
                System.out.println("a similar ID has been detected, generating a new one");
            }
        }
        return id;
    }


    //SYSTEMETS RESPONS TIL USER DER VIL FJERNE MEMBER
    private void removeMember() {
        scanner.nextLine();
        System.out.print("Enter the name of the member you want to delete: ");
        String name = scanner.nextLine();
        controller.removeMember(name);

        System.out.println("Member deleted successfully");
    }

    //METODE TIL AT UDPRINTE LISTE AF MEMBERS
    private void printMembers() {
        System.out.println(controller.printMembers());
    }

    //METODE TIL AT VÆLGE HVEM DER SKAL REDIGERES
    private void editMember() {
        System.out.print("Enter the name of the member you want to edit: ");
        String name = scanner.nextLine();
        int counter = 0;

        scanner.nextLine();
        ArrayList<Member> memberArrayList = controller.searchMembers(name);


        if (!memberArrayList.isEmpty()) {
            for (Member member : memberArrayList) {
                counter++;
                System.out.println(counter + ". " + member);
            }
            if (memberArrayList.size() >= 2) {
                int input = getIntInput("Choose a number that is referring to the member you want to edit: ");
                Member memberToEdit = memberArrayList.get(input - 1);
                editHelper(memberToEdit);
            } else {
                Member memberToEdit = memberArrayList.getFirst();
                editHelper(memberToEdit);
            }
        } else {
            System.out.println("There are no members called that ");
        }
        controller.saveMemberList();
    }

    //METODE TIL AT REDIGERE MEMBERS INFORMATION (UNDTAGEN UNIKT ID)
    private void editHelper(Member member) {
        System.out.print("Enter the new name of the member: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            member.setName(name);
        }
        System.out.println("Enter the new type of swimmer the member is: ");
        String swimType = scanner.nextLine();
        if (!swimType.isEmpty()) {
            member.setSwimtype(swimType);
        }
        System.out.println("Enter the new type of member: 1 is for aktiv, 2 is for passiv");
        boolean aktiv = true;
        if (scanner.nextInt() == 1) {
            aktiv = true;
        } else {
            aktiv = false;
        }

        System.out.println("Enter the new age of the member: ");
        int age = getIntInput("");
        member.setAge(age);

        System.out.println("Enter the type activity form for the member you want to edit, 1 for motionist, 2 for konkurrencesvømmer: ");
        boolean motionist = true;
        if (scanner.nextInt() == 1) {
            motionist = true;
        } else {
            motionist = false;
        }
        member.setMotKon(motionist);

        System.out.println("Member edited successfully");
    }

    //'SWITCH-CASE' TIL FORSKELLIGE SORTERING AF MEMBERS
    private void sortMembers() {
        boolean running = false;
        while (!running) {
            System.out.println("-------------------------------------------------");
            System.out.println("1) Sort by name");
            System.out.println("2) sort by age");
            System.out.println("3) sort by membertype");
            System.out.println("4) sort by swimtype");
            System.out.println("5) sort by memberID");
            System.out.println("6) exit");
            System.out.println("-------------------------------------------------");


            int choice = getIntInput("Choose an option:");
            switch (choice) {
                case 1 -> {
                    controller.sortByName();
                    running = true;
                }
                case 2 -> {
                    controller.sortByAge();
                    running = true;
                }
                case 3 -> {
                    controller.sortByMemberType();
                    running = true;
                }
                case 4 -> {
                    controller.sortBySwimType();
                    running = true;
                }
                case 5 -> {
                    controller.sortByID();
                    running = true;
                }
                case 6 -> {
                    running = true;
                }
            }
        }
    }

    //METODE TIL AT SEARCHE EFTER ET MEMBER
    private void findMember() {
        scanner.nextLine();
        System.out.println("Enter the name of the member you want to find");
        String name = scanner.nextLine();
        System.out.println(controller.findMember(name));
    }

    private void formandUserInterface() {
        boolean exit = false;
        while (!exit) {
            System.out.println("-------------------------------------------------");
            System.out.println("1) Add member to the list");
            System.out.println("2) Remove member from the list");
            System.out.println("3) View all members on the list");
            System.out.println("4) Edit a members information");
            System.out.println("5) Sort the arrangement of members on the list");
            System.out.println("6) Search for a specific member on the list");
            System.out.println("7) Exit");
            System.out.println("-------------------------------------------------");


            int choice = getIntInput("Choose an option:");
            switch (choice) {
                case 1 -> addMember();
                case 2 -> removeMember();
                case 3 -> printMembers();
                case 4 -> editMember();
                case 5 -> sortMembers();
                case 6 -> findMember();
                case 7 -> exit = true;
            }
        }
    }

    private void kassererUserInterface() {
        boolean exit = false;
        while (!exit) {
            System.out.println("-------------------------------------------------");
            System.out.println("1) Information om diverse kontingenter");
            System.out.println("2) Generation af samlet oversigt af forventede kontingenter");
            System.out.println("3) Informtion om medlemmer i restance");
            System.out.println("4) Exit");
            System.out.println("-------------------------------------------------");


            int choice = getIntInput("Choose an option:");
            switch (choice) {/*
                case 1 -> ;*/
                case 2 -> System.out.println("Total kontigent: " + controller.kontigentBeregner() + " kr.");
                case 3 -> System.out.println(controller.getRestance());
                case 4 -> exit = true;
            }
        }
    }

    private void trænerUserInterface() {
        boolean exit = false;
        while (!exit) {
            System.out.println("-------------------------------------------------");
            System.out.println("1) Information om svømmere");
            System.out.println("2) Oversigt af svømmernes discipliner og resultater");
            System.out.println("3) Registrering af svømmeresulter inden for træning");
            System.out.println("4) Registrering af svømmers konkurrence resultater");
            System.out.println("5) Information om de bedste fem svømmere for hver disciplin");
            System.out.println("6) Exit");
            System.out.println("-------------------------------------------------");


            int choice = getIntInput("Choose an option:");
            switch (choice) {
                case 1 -> {
                    while (true) {
                        System.out.println("1) Junior konkurrencehold");
                        System.out.println("2) Senior konkurrencehold");

                        int choice2 = getIntInput("choose an option");
                        switch (choice2) {
                            case 1 -> System.out.println(controller.printJuniorElites());
                            case 2 -> System.out.println(controller.printSeniorElites());
                        }
                        break;
                    }
                }
                case 2 -> printAllSwimResults();
                case 3 -> addTrainingInfo();
                case 4 -> addKonkurrenceInfo();
                case 5 -> showTop5Swimmers();
                case 6 -> exit = true;
            }
        }
    }

    private void printAllSwimResults() {
        ArrayList<Member> members = controller.getMembers();

        if (members.isEmpty()) {
            System.out.println("Der er ingen medlemmer i systemet.");
            return;
        }

        for (Member member : members) {
            System.out.println("-------------------------------------------------");
            System.out.println("Navn: " + member.getName());
            System.out.println("Alder: " + member.getAge());
            System.out.println("Disciplin: " + member.getSwimType());
            System.out.println("Medlemstype: " + (member.getMembertype() ? "Aktiv" : "Passiv"));
            System.out.println("Træningsresultater: ");

            String trainingResults = member.getTrainingResults();
            if (trainingResults == null || trainingResults.isEmpty()) {
                System.out.println(" Ingen træningsresultater registreret.");
            } else {
                String[] results = trainingResults.split(";");
                for (String result : results) {
                    System.out.println("  " + result.trim());
                }
            }
            System.out.println("Konkurrenceresultater: ");

            String competitionResults = member.getCompetitionResults();
            if (competitionResults == null || competitionResults.isEmpty()) {
                System.out.println("  Ingen konkurrenceresultater registreret.");
            } else {
                String[] results = competitionResults.split(";");
                for (String result : results) {
                    System.out.println("  " + result.trim());
                }
            }
            System.out.println("-------------------------------------------------");

        }
    }

    private void addTrainingInfo() {
        System.out.print("Enter navnet på det medlem du vil registrere træningsresultater til:  \n");
        String name = scanner.nextLine();
        int counter = 0;

        ArrayList<Member> memberArrayList = controller.searchEliteMembers(name);

        if (!memberArrayList.isEmpty()) {
            for (Member member : memberArrayList) {
                counter++;
                System.out.println(counter + ". " + member);
            }
            if (memberArrayList.size() >= 2) {
                int input = getIntInput("Vælg et nummer der referer til det medlem du ønsker at ændre: ");
                Member memberToEdit = memberArrayList.get(input - 1);
                scanner.nextLine();
                System.out.println("Indtast tiden fra træningen i sekunder:");
                String string = scanner.nextLine();
                LocalDate date = LocalDate.now();
                memberToEdit.addTrainingResults(date + " " + memberToEdit.getSwimType() + " tid: " + string);
            } else {
                Member memberToEdit = memberArrayList.getFirst();
                System.out.println("Indtast tiden fra træningen i sekunder:");
                String string = scanner.nextLine();
                LocalDate date = LocalDate.now();
                memberToEdit.addTrainingResults(date + " " + memberToEdit.getSwimType() + " tid: " + string);
            }
        } else {
            System.out.println("Der er ingen medlemmer med det navn ");
        }
        controller.saveMemberList();
    }

    private void addKonkurrenceInfo() {
        System.out.print("Enter navnet på det medlem du vil registrere konkurrenceresultater til:  \n");
        String name = scanner.nextLine();
        int counter = 0;

        ArrayList<Member> memberArrayList = controller.searchEliteMembers(name);

        if (!memberArrayList.isEmpty()) {
            for (Member member : memberArrayList) {
                counter++;
                System.out.println(counter + ". " + member);
            }
            if (memberArrayList.size() >= 2) {
                int input = getIntInput("Vælg et nummer der referer til det medlem du ønsker at ændre: ");
                Member memberToEdit = memberArrayList.get(input - 1);
                scanner.nextLine();
                System.out.println("Indtast tiden fra konkurrencen i sekunder");
                String string = scanner.nextLine();
                LocalDate date = LocalDate.now();
                System.out.println("Indtast navnet op stævnet der blev deltaget i: ");
                String stævne = scanner.nextLine();
                int placering = getIntInput("Indtast medlemmets placering i stævnet: ");
                memberToEdit.addCompetitionResults(date + " " + memberToEdit.getSwimType() + " tid: " + string + "sekunder" + "Stævne: " + stævne + "Placering: " + placering);
            } else {
                Member memberToEdit = memberArrayList.getFirst();
                System.out.println("Indtast tiden fra konkurrencen i sekunder:");
                String string = scanner.nextLine();
                LocalDate date = LocalDate.now();
                System.out.println("Indtast navnet op stævnet der blev deltaget i: ");
                String stævne = scanner.nextLine();
                int placering = getIntInput("Indtast medlemmets placering i stævnet: ");
                memberToEdit.addCompetitionResults(date + " " + memberToEdit.getSwimType() + " tid: " + string + "sekunder" + "Stævne: " + stævne + "Placering: " + placering);
            }
        } else {
            System.out.println("Der er ingen medlemmer med det navn ");
        }
        controller.saveMemberList();
    }

    private void showTop5Swimmers() {
        System.out.println("Vælg en svømmedisciplin:");
        System.out.println("1) Crawl");
        System.out.println("2) Rygcrawl");
        System.out.println("3) Butterfly");
        System.out.println("4) Brystsvømning");

        String swimType = "";
        int choice = getIntInput("Vælg discplin: ");

        switch (choice) {
            case 1 -> swimType = "Crawl";
            case 2 -> swimType = "Rygcrawl";
            case 3 -> swimType = "Butterfly";
            case 4 -> swimType = "Brystsvømning";
            default -> {
                System.out.println("Ugyldigt valg.");
                return;
            }
        }

        System.out.println("Vælg aldersgruppe:");
        System.out.println("1) Junior");
        System.out.println("2) Senior");

        boolean isJunior = getIntInput("Vælg aldersgruppe: ") == 1;
        ArrayList<Member> top5 = controller.getMemberList().getTop5Swimmers(swimType, isJunior);
        if (top5.isEmpty()) {
            System.out.println("Ingen svømmere fundet for denne kategori.");
        } else {
            System.out.println("Top 5 svømmere i " + swimType + " (" + (isJunior ? "Junior" : "Senior") + "):");
            for (int i = 0; i < top5.size(); i++) {
                System.out.println((i + 1) + ". " + top5.get(i));
            }
        }
    }

    //RESPONDERER TIL USERS LOGIN OM DER ER TILLADELSE FOR ADGANG
    private boolean checkLogIn(String username, String password) {
        boolean logIn = false;
        boolean foundUser = false;
        for (UserLogIn userLogIn : controller.readLogInFile()) {
            if (userLogIn.getUsername().equalsIgnoreCase(username) && userLogIn.getPassword().equalsIgnoreCase(password)) {
                foundUser = true;
            }
        }
        if (foundUser == true) {
            logIn = true;
        } else if (foundUser == false) {
            System.out.println("Ugyldigt brugernavn eller kodeord, prøv igen");
        }
        return logIn;
    }

}
