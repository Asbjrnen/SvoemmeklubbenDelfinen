package Models;

public class Member {
    private int id;
    private String name;
    private int age;
    private String svimtype;
    private String membertype;
    //MEMBERTYPE KAN LAVES TIL BOOLEAN DA DEN KUN VÆLGER MELLEM 2 MEDLEMSKABSTYPER (AKTIV/PASSIV)
    private boolean junSen;
    private boolean motKon;
    private boolean isRes;

    // KONSTRUKTØR TIL INITIALISERING AF MEDLEMMER I KLUBBEN
    public Member(String name, int age, String svimtype, String membertype, int id, boolean junSen, boolean motKon, boolean isRes) {
        this.isRes = isRes;
        this.id = id;
        this.name = name;
        this.age = age;
        this.svimtype = svimtype;
        this.membertype = membertype;
        this.junSen = junSen;
        this.motKon = motKon;
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
    public boolean isJunSen() {
        return junSen;
    }
    public void setJunSen(boolean junior) {
        this.junSen = junior;
    }
    public boolean isMotKon() {
        return motKon;
    }
    public void setMotKon(boolean motion) {
        this.motKon = motion;
    }

    public boolean isRes() {
        return isRes;
    }
    public void setRes(boolean restance) {
        isRes = restance;
    }

    // OVERRIDE AF 'toString()' FOR AT RETURNERE INFO OM MEDLEMMER
    @Override
    public String toString() {
    return /*"Id: " + id +*/ "Name: " + name + ", Age: " + age + ", Svimtype: " + svimtype + ", Membertype: " + membertype
            + ", Activity form: " + motKon + ", Teamtype: " + junSen;
    }
    //SOUT med isRes skal skrives til formand og kasserer

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
