package Data_source;

import Models.Member;
import Models.UserLogIn;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private File memberListFile = new File("Memberlist.txt");
    private File logInFile = new File("UserLogin.txt");


//MULIGGØRER TILFØJELSE AF MEMBER TIL MEMBERLIST.TXT
    public ArrayList<Member> readFileMemberList(){
        ArrayList<Member> memberList = new ArrayList<>();
        try(Scanner scanner = new Scanner(memberListFile)) {
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] words = line.split(",");
                String name = words[0];
                int age = Integer.parseInt(words[1]);
                String svimtype = words[2];
                boolean membertype = Boolean.parseBoolean(words[3]);
                int id = Integer.parseInt(words[4]);
                boolean junSen = Boolean.parseBoolean(words[5]);
                boolean motKon = Boolean.parseBoolean(words[6]);
                boolean isRes = Boolean.parseBoolean(words[7]);

                memberList.add(new Member(name, age, svimtype, membertype, id, junSen, motKon, isRes));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }
//OPRETTELSE AF ADGANGSPORTAL TIL FORSKELLIGE USERS
    public ArrayList<UserLogIn> readUserLogIn(){
        ArrayList<UserLogIn> userLogInList = new ArrayList<>();
        try(Scanner scanner = new Scanner(logInFile)) {
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] words = line.split(",");
                String username = words[0];
                String password = words[1];
                userLogInList.add(new UserLogIn(username,password));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userLogInList;
    }
//GEMMER FORSKELLIGE MEMBERS TIL MEMBERLIST.TXT
    public void saveFile(ArrayList<Member> memberList){
        try {
            FileWriter fileWriter = new FileWriter("Memberlist.txt");
            for(Member member : memberList){
                fileWriter.write(member.getName()+","+member.getAge()+","+member.getSvimType()+","+member.getMembertype()+","
                        +member.getId()+","+member.getKontigent()+","+member.isRes()+"\n");
            }
            fileWriter.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
