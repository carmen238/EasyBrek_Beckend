package com.tesi.Entity;

import java.util.Date;

public class Token {

    private String Token;
    private Date expirationDateToken;
    private int idUser;
    private int idAdmin;
    String typeUser;

    public Token(String token, Date expirationDateToken,int idUser ) {
        Token = token;
        this.expirationDateToken = expirationDateToken;
        this.idUser = idUser;
    }

    public Token(String token, int idUser ) {
        Token = token;
        this.idUser = idUser;
    }

    public Token(String token, int idUser, String typeUser ) {
        Token = token;
        this.idUser = idUser;
        this.typeUser = typeUser;
    }

    public String getToken() {
        return Token;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }


    public Date getExpirationDateToken() {
        return expirationDateToken;
    }

    public void setExpirationDateToken(Date expirationDateToken) {
        this.expirationDateToken = expirationDateToken;
    }


}
