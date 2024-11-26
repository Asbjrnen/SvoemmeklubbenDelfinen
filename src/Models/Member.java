package Models;

public class Member {
    private int id;
    private String name;
    private int age;
    private String svimtype;
    private String membertype;

    // KONSTRUKTØR TIL INITIALISERING AF MEDLEMMER I KLUBBEN
    public Member(String name, int age, String svimtype, String membertype, int id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.svimtype = svimtype;
        this.membertype = membertype;
    }

    // GETTERS OG SETTERS FOR MEMBERS
    public int getId() {
        return id;
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
    //2 forskellige svømmetyper - junior/senior
    public String getSvimType() {
        return svimtype;
    }
    public void setSvimtype(String Svimtype) {
        this.svimtype = Svimtype;
    }
    //2 forskellige medlemstyper - aktivt/passivt
    public String getMembertype() {
        return membertype;
    }
    public void setMembertype(String membertype) {
        this.membertype = membertype;
    }

    // OVERRIDE AF 'toString()' FOR AT RETURNERE INFO OM MEDLEMMER
    @Override
    public String toString() {
    return /*"Id: " + id +*/ "Name: " + name + ", Age: " + age + ", Svimtype: " + svimtype + ", Membertype: " + membertype;
    }

}
