package Data_source;

import Models.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private File memberListFile = new File("Memberlist.txt");



    public ArrayList<Member> readFileMemberList(){
        ArrayList<Member> memberList = new ArrayList<>();
        try(Scanner scanner = new Scanner(memberListFile)) {
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] words = line.split(",");
                String name = words[0];
                int age = Integer.parseInt(words[1]);
                String svimtype = words[2];
                String membertype = words[3];
                int id = Integer.parseInt(words[4]);

                memberList.add(new Member(name, age, svimtype, membertype, id));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }

    public void saveFile(ArrayList<Member> memberList){
        try {
            FileWriter fileWriter = new FileWriter("Memberlist.txt");
            for(Member member : memberList){
                fileWriter.write(member.getName()+","+member.getAge()+","+member.getSvimType()+","+member.getMembertype()+"\n");
            }
            fileWriter.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
