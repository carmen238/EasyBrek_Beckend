package com.tesi.Entity;

import org.apache.el.parser.Token;

public class Login {
   private String Email;
   private String Password;
   private String TypeUser;
   private String Token;
    private String Error;

    public Login(String email, String password, String typeUser) {
        Email = email;
        Password = password;
        TypeUser = typeUser;
    }
    public Login(String token) {
        Token = token;
    }
    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTypeUser() {
        return TypeUser;
    }

    public void setTypeUser(String typeUser) {
        TypeUser = typeUser;
    }
}
