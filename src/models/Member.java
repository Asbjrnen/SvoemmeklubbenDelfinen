package models;

public class Member {
    private int id;
    private String name;
    private int age;
    private String svimtype;
    private boolean membertype;
    private boolean motKon;
    private boolean isRes;
    private String trainingResults;
    private String competitionResults;

    // KONSTRUKTØR TIL INITIALISERING AF MEDLEMMER I KLUBBEN
    public Member(String name, int age, String svimtype, boolean membertype, int id, boolean motKon, boolean isRes) {
        this.isRes = isRes;
        this.id = id;
        this.name = name;
        this.age = age;
        this.svimtype = svimtype;
        this.membertype = membertype;
        this.motKon = motKon;
    }

    public Member(String name, int age, String svimtype, boolean membertype, int id, boolean motKon, boolean isRes,
                  String trainingResults, String competitionResults) {
        this.isRes = isRes;
        this.id = id;
        this.name = name;
        this.age = age;
        this.svimtype = svimtype;
        this.membertype = membertype;
        this.motKon = motKon;
        this.trainingResults = trainingResults;
        this.competitionResults = competitionResults;
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
    public String getSwimType() {
        return svimtype;
    }

    public void setSwimtype(String Svimtype) {
        this.svimtype = Svimtype;
    }

    //2 forskellige medlemstyper - aktivt/passivt
    public boolean getMembertype() {
        return membertype;
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

    public void addTrainingResults(String trainingResults) {
        this.trainingResults += ";" + trainingResults;
    }

    public String getTrainingResults() {
        return trainingResults;
    }

    public void addCompetitionResults(String competitionResults) {
        this.competitionResults += competitionResults + ";";
    }

    public String getCompetitionResults() {
        return competitionResults;
    }

    public double getKontigent() {
        double kontigent = 0.0;
        double juniorKon = 1000.0;
        double senKon = 1600.0;
        double sen60 = 0.25;
        double passivKon = 500.0;

        if (membertype == false) {
            kontigent += passivKon;
        } else {
            if (age < 18) {
                kontigent += juniorKon;
            } else if (age >= 60) {
                kontigent += senKon * sen60;
            } else {
                kontigent += senKon;
            }
        }
        return kontigent;
    }

    public double getBestTrainingResult() {
        if (trainingResults == null || trainingResults.isEmpty()) {
            return Double.MAX_VALUE;
        }

        String[] results = trainingResults.split(";");
        double bestResult = Double.MAX_VALUE;

        for (String result : results) {
            try {
                double time = Double.parseDouble(result.trim());
                if (time < bestResult) {
                    bestResult = time;
                }
            } catch (NumberFormatException e) {
                // DEN IGNORERER, HVIS DER OPSTÅR FEJL
            }
        }
        return bestResult;
    }


    // OVERRIDE AF 'toString()' FOR AT RETURNERE INFO OM MEDLEMMER
    @Override
    public String toString() {
        String jS = "";
        if (age < 18) {
            jS = "junior";
        } else {
            jS = "senior";
        }
        String mK = "";
        if (motKon) {
            mK = "Motionist";
        } else {
            mK = "Konkurrencesvømmer";
        }

        String mT = "";
        if (membertype) {
            mT = "Aktiv";
        } else {
            mT = "Passiv";
        }
        return "Name: " + name + ", Age: " + age + ", Svimtype: " + svimtype + ", Membertype: " + mT
                + ", Activity form: " + mK + ", Teamtype: " + jS;

    }
}



