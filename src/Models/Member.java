package Models;

public class Member {
    private int id;
    private String name;
    private int age;
    private String Svimtype;

    public Member(int id, String name, int age, String Svimtype) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.Svimtype = Svimtype;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSvimtype() {
        return Svimtype;
    }
    public void setSvimtype(String Svimtype) {
        this.Svimtype = Svimtype;
    }
}
