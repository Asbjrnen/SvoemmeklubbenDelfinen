package Models;

public class Member {
//    private int id;
    private String name;
    private int age;
    private String svimtype;
    private String membertype;

    public Member(String name, int age, String svimtype, String membertype) {
//        this.id = id;
        this.name = name;
        this.age = age;
        this.svimtype = svimtype;
        this.membertype = membertype;
    }
//    public int getId() {
//        return id;
//    }
//    public void setId(int id) {
//        this.id = id;
//    }
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
    public String getSvimtype() {
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
    @Override
    public String toString() {
    return /*"Id: " + id +*/ "Name: " + name + ", Age: " + age + ", Svimtype: " + svimtype + ", Membertype: " + membertype;
    }

}
