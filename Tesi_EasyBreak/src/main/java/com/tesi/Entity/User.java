package com.tesi.Entity;


public class User{


    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String oldPassword;
    private String typeUser;
    private String companyName;
    private boolean enabled;

    public User( String name, String surname, String email, String password, String typeUser) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;
    }

    public User( String name, String surname, String email, String password, String typeUser ,String companyName, Boolean enabled) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;
        this.companyName = companyName;
        this.enabled = enabled;
    }
    public User(int id, String name, String surname, String email , String companyName, String typeUser) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.companyName = companyName;
        this.typeUser = typeUser;
    }
    public User( int id,String name, String surname, String email, String typeUser ,String companyName, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.typeUser = typeUser;
        this.companyName = companyName;
        this.enabled = enabled;
    }

    public User( String password, String oldPassword) {
        this.password = password;
        this.oldPassword = oldPassword;
    }

    public User( String name) {
        this.name = name;

    }
    public User( ) {

    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", typeuser=" + typeUser +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getTypeUser() {
        return typeUser;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }


}
