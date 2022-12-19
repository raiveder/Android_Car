package com.example.car;

public class Accounts {

    private int Id_user;
    private String Login;
    private String Password;

    public Accounts(int id_user, String login, String password) {

        if (Id_user != 0) {
            Id_user = id_user;
        }
        Login = login;
        Password = password;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getId() {
        return Id_user;
    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }
}
