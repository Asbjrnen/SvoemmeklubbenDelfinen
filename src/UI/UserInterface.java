package UI;

import Models.Controller;
import Models.Member;
import Models.UserLogIn;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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
                case 4 -> forældreUserInterface();
                case 5 -> exit = true;
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
//RESPONDERER TIL USERS LOGIN OM DER ER TILLADELSE FOR ADGANG
    private boolean checkLogIn(String username, String password){
        boolean logIn = false;
        boolean foundUser = false;
        for (UserLogIn userLogIn : controller.readLogInFile()){
            if (userLogIn.getUsername().equalsIgnoreCase(username) && userLogIn.getPassword().equalsIgnoreCase(password)) {
                foundUser = true;
            }
        }
        if (foundUser == true){
            logIn = true;
        } else if (foundUser == false){
            System.out.println("Invalid username or password. Please try again.");
        }
        return logIn;
    }
//TILFØJER ET MEMBER TIL MEMBERLIST.TXT
    private void addMember() {
        scanner.nextLine();
        System.out.println("Please enter the name of the member you would like to add: ");
        String name = scanner.nextLine();

        System.out.println("Please enter the type of swimmer the member you would like to add is: ");
        String swimType = scanner.nextLine();

        System.out.println("Please enter the type of member you would like to add: ");
        String memType = scanner.nextLine();

        int age = getIntInput("Please enter the age of the member you would like to add: ");

        System.out.println("A numerical ID will now be generated for the member");
        int id = generateUniqueID();

        boolean junSen = getIntInput("\"Please enter whether the member is a senior or a junior: \"\\n 1 for junior or 0 for senior\"") == 1;

        boolean motKon = getIntInput("Please enter the activity form your member is using: \"\\n 1 for motionist or 0 for konkurrencesvømmer\"") == 1;

        boolean isRes = getIntInput("Please enter wether the member is in restance or not: \"\\n 1 for yes or 2 for no\"") == 1;


        System.out.println("MemberID: " + id);
        controller.addMember(new Member(name, age, swimType, memType, id, junSen, motKon, isRes));
    }
//GENERER ET RANDOM ID TIL ET OPRETTET MEMBER
    private int generateUniqueID(){
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
            member.setSvimtype(swimType);
        }
        System.out.println("Enter the new type of member: ");
        String memType = scanner.nextLine();
        if (!memType.isEmpty()) {
            member.setMembertype(memType);
        }
        System.out.println("Enter the new age of the member: ");
        int age = getIntInput("");
        member.setAge(age);

        System.out.println("Enter the type of team the member you want to edit is on, 1 for junior, 2 for senior: ");
        boolean junior = true;
        if (scanner.nextInt() == 1){
            junior = true;
        } else{
            junior = false;
        }
        member.setJunSen(junior);

        System.out.println("Enter the type activity form for the member you want to edit, 1 for motionist, 2 for konkurrencesvømmer: ");
        boolean motionist = true;
        if (scanner.nextInt() == 1){
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

    private void formandUserInterface(){
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
    private void kassererUserInterface(){
        boolean exit = false;
        while (!exit) {
            System.out.println("-------------------------------------------------");
            System.out.println("1) Information om diverse kontingenter");
            //Skal måske lede videre til specifik kontingent? (Skal kunne baseres på aldersrabat, aktivitetsform og medlemskabstype)
            System.out.println("2) Generation af samlet oversigt af forventede kontingenter");
            //Samlede ÅRLIGE kontingent - til vurdering af klubbens økonomi
            System.out.println("3) Informtion om medlemmer i restance");
            //Til opfølging af ubetalte kontingenter
            System.out.println("4) Exit");
            System.out.println("-------------------------------------------------");


            int choice = getIntInput("Choose an option:");
            switch (choice) {/*
                case 1 -> ;
                case 2 -> ;
                case 3 -> ;*/
                case 4 -> exit = true;
            }
        }
    }
    private double kontingentBeregner(int junior, int senior, int senior60, int passiv){
        double juniorKon = 1000.0;
        double senKon = 1600.0;
        double sen60 = 0.25;
        double passivKon = 500.0;

        double junTotal = junior * juniorKon;
        double senTotal = senior * senKon;
        double passivTotal = passiv * passivKon;
        double senTotal = senior60 * (senKon* (1-sen60));


    }
    private void trænerUserInterface(){
        boolean exit = false;
        while (!exit) {
            System.out.println("-------------------------------------------------");
            System.out.println("1) Vælg en svømmedisciplin til et specifikt medlem");
            System.out.println("2) Oversigt af svømmernes discipliner og resultater");
            System.out.println("3) Information om svømmere");
            //Se trello for information (2. træner userstory)
            System.out.println("4) Registrering af svømmeresulter inden for træning");
            //Bedste træningsresultater med dato og tid
            System.out.println("5) Registrering af konkurrenceresultator for svømmere");
            //Svømmernes konkurrenceresultater, herunder; stævne, tid, dato og placering
            System.out.println("6) Information om seniorhold");
            System.out.println("7) Information om juniorhold");
            System.out.println("8) Information om de bedste fire svømmere fra hvert hold");
            //Skal indeholde information om de fire bedste svømmere fra hvert hold (Tid, placering, alder)
            System.out.println("-------------------------------------------------");


            int choice = getIntInput("Choose an option:");
            switch (choice) {/*
                case 1 -> ;
                case 2 -> ;
                case 3 -> ;
                case 4 -> ;
                case 5 -> ;
                case 6 -> ;
                case 7 -> ;
                case 8 -> ;*/
                case 9 -> exit = true;
            }
        }
    }
    private void forældreUserInterface(){
        boolean exit = false;
        while (!exit) {
            System.out.println("-------------------------------------------------");
            System.out.println("1) ");
            System.out.println("2) ");
            System.out.println("3) ");
            System.out.println("4) ");
            System.out.println("5) ");
            System.out.println("6) ");
            System.out.println("7) ");
            System.out.println("-------------------------------------------------");


            int choice = getIntInput("Choose an option:");
            switch (choice) {/*
                case 1 -> ;
                case 2 -> ;
                case 3 -> ;
                case 4 -> ;
                case 5 -> ;
                case 6 -> ;*/
                case 7 -> exit = true;
            }
        }
    }
}



/* package UI;

import Data_source.LoginSystem;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private LoginSystem loginSystem;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.loginSystem = new LoginSystem();
    }

    public void startProgram() {
        System.out.println("Welcome to Svømmeklubben Delfinen.");
        String role = login();

        if (role != null) {
            boolean exit = false;
            while (!exit) {
                switch (role.toLowerCase()) {
                    case "formand":
                        showFormandMenu();
                        break;
                    case "kasserer":
                        showKassererMenu();
                        break;
                    case "træner":
                        showTrænerMenu();
                        break;
                    case "forælder":
                        showForælderMenu();
                        break;
                    default:
                        System.out.println("Unknown role.");
                        exit = true;
                }
            }
        } else {
            System.out.println("Login failed. Exiting program.");
        }
    }

    private String login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return loginSystem.login(username, password);
    }

    private void showFormandMenu() {
        System.out.println("Formand Menu:");
        System.out.println("1) Add member");
        System.out.println("2) Remove member");
        // Formandens handlinger...
    }

    private void showKassererMenu() {
        System.out.println("Kasserer Menu:");
        System.out.println("1) View kontingent");
        System.out.println("2) Update kontingent status");
        // Kassererens handlinger...
    }

    private void showTrænerMenu() {
        System.out.println("Træner Menu:");
        System.out.println("1) View svømmeres resultater");
        System.out.println("2) Update træningsresultater");
        // Trænerens handlinger...
    }

    private void showForælderMenu() {
        System.out.println("Forælder Menu:");
        System.out.println("1) View junior svømmer resultater");
        // Forældrenes handlinger...
    }
}
*/

///////////////////////////////////////////////////////////////////////////
/*
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
    System.out.println("Please enter the type of member you would like to add: ");
    String memType = "";
    boolean exit2 = false;
    while (!exit2) {
        System.out.println("-------------------------------------------------");
        System.out.println("1) Aktiv");
        System.out.println("2) Passiv");
        System.out.println("-------------------------------------------------");

        int choice = getIntInput("Choose an option:");
        switch (choice) {
            case 1 -> {
                memType = "aktiv";
                exit2 = true;
            }
            case 2 -> {
                memType = "passiv";
                exit2 = true;
            }
        }
    }

    int age = getIntInput("Please enter the age of the member you would like to add: ");

    System.out.println("A numerical ID will now be generated for the member");
    int id = generateUniqueID();

    System.out.println("MemberID: " + id);
    controller.addMember(new Member(name, age, swimType, memType, id));
}
*/