package models;

public class UserLogIn {
    //instansvariabler
    private String username;
    private String password;

    //konstruktør til initialisering af userLogIn
    public UserLogIn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
