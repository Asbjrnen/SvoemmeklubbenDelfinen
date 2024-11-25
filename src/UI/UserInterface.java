package UI;

import Models.Controller;
import Models.Member;
import Models.UserLogIn;

import java.io.IOException;
import java.sql.SQLOutput;
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

        System.out.println("MemberID: " + id);
        controller.addMember(new Member(name, age, swimType, memType, id));
    }

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



    private void removeMember() {
        scanner.nextLine();
        System.out.print("Enter the name of the member you want to delete: ");
        String name = scanner.nextLine();
        controller.removeMember(name);

        System.out.println("Member deleted successfully");
    }

    private void printMembers() {
        System.out.println(controller.printMembers());
    }

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

        System.out.println("Member edited successfully");
    }

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

    private void findMember() {
        scanner.nextLine();
        System.out.println("Enter the name of the member you want to find");
        String name = scanner.nextLine();
        System.out.println(controller.findMember(name));
    }

}
