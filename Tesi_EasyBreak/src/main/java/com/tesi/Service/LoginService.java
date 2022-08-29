package com.tesi.Service;

import com.jdbcConnection;
import com.tesi.Entity.Dish;
import com.tesi.Entity.Response;
import com.tesi.Entity.Token;
import com.tesi.Entity.User;
import com.tesi.Utility.ManageToken;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginService {
      //jdbc instance
      jdbcConnection conn = new jdbcConnection().getInstance();
       public Response checkUser(String email, String password, String typeClient) throws SQLException, ClassNotFoundException {

           //query check to see if exists the user with that email into db
           String query = "Select * from client where email = ?";
           String query1 = "Select * from admin where email = ?";
           PreparedStatement myState;


           if(typeClient.equals("client")){
               myState = conn.getConnection().prepareStatement(query);
           }
           else
               myState = conn.getConnection().prepareStatement(query1);
           myState.setString(1, email);
           ResultSet myRes = myState.executeQuery();


           //I encode the password to check if the password after verified
           //that the user with that email exists
           PasswordService pass = new PasswordService();
           String passordEncoded= pass.encryptPassword(password, "Vin0DellaCas4");
           if(!myRes.next())
               return  new Response("Utente inesistente","");
           else {
               if (myRes.getString("password").equals(passordEncoded) ) {
                   int idUser = myRes.getInt("id");
                   //after verified the conditions the function creates and returns the token
                   ManageToken token = new ManageToken();
                   Token newToken = token.generateToken( password,email, "Vin0DellaCas4",idUser);
                   if(typeClient.equals("client"))
                      return new Response(newToken, myRes.getInt("adminId"));
                   else
                       return new Response(newToken);
               }

               else if(!myRes.getBoolean("enabled"))
                   return  new Response("Utente non attivo","");
               else return  new Response("Password errata","");

               }
       }


       public String checkToken(String tokenFake){
           ManageToken mt = new ManageToken();
           if (mt.extractKey(tokenFake) == "Vin0DellaCas4")
               return mt.extractKey(tokenFake);
           return mt.extractKey(tokenFake);
       }

    public Response getUser (Token token, String typeUser) throws SQLException, ClassNotFoundException {

        //i take the user through the id
        String queryGetUserClient = "Select * from client where id = ?";
        String queryGetUserAdmin = "Select * from admin where id = ?";

        //execute select query
        PreparedStatement myState;
        PreparedStatement myState1;

        ManageToken mt = new ManageToken();

        //declare the user that i'll return
        User u = new User();
        String companyName= "";

        if(mt.checkToken(token).getResponse() == "true") {
            if(typeUser.equals("client")) {
                myState = conn.getConnection().prepareStatement(queryGetUserClient);

            }else
                myState = conn.getConnection().prepareStatement(queryGetUserAdmin);

                myState.setInt(1,token.getIdUser());
                ResultSet myRes = myState.executeQuery();

            while (myRes.next()) {
                if(typeUser.equals("client")){
                    myState1 =conn.getConnection().prepareStatement(queryGetUserAdmin);
                    myState1.setInt(1,myRes.getInt("adminId"));
                    ResultSet myRes1 = myState1.executeQuery();
                    if(myRes1.next())
                       companyName=  myRes1.getString("companyName");
                }else{
                    companyName = myRes.getString("companyName");
                }

                u = new User(token.getIdUser(), myRes.getString("name"), myRes.getString("surname"), myRes.getString("email"),companyName,typeUser, myRes.getBoolean("enabled"));

            }
                return new Response(u);

        }
        else return new Response(mt.checkToken(token).getResponse());

    }


    public Response changeEmail (Token token, String newEmail, String typeUser) throws SQLException, ClassNotFoundException {

        //i take the user through the id of admin
        String queryUpdateMailClient = "UPDATE client SET email=? WHERE id=?";
        String queryUpdateMailAdmin = "UPDATE admin SET email=? WHERE id=?";

        //execute select query
        PreparedStatement pstate;

        ManageToken mt = new ManageToken();

        if(mt.checkToken(token).getResponse() == "true") {
            if(typeUser.equals("client")) {
                 pstate = conn.getConnection().prepareStatement(queryUpdateMailClient);
            }else {
                 pstate = conn.getConnection().prepareStatement(queryUpdateMailAdmin);
            }
            pstate.setString(1,newEmail);
            pstate.setInt(2,token.getIdUser());
            int rowAffected = pstate.executeUpdate();
            rowAffected = pstate.executeUpdate();

            return new Response("Aggiornamento dell'email effettuato con successo!");

        }
        else return new Response(mt.checkToken(token).getResponse());

    }

    public Response changePassword(Token token,String oldPassword ,String password) throws SQLException, ClassNotFoundException {

        //i update the password through the old password and id
        String queryUpdatePasswordClient = "UPDATE client SET password=? WHERE id=? AND password=?";
        String queryUpdatePasswordAdmin = "UPDATE admin SET password=? WHERE id=? AND password=?";
        String typeClient= token.getTypeUser();
        PreparedStatement myState;

        //I encode the password to check if the oldPassword is corret so the user can will change it
        PasswordService pass = new PasswordService();
        String newPassordEncoded = pass.encryptPassword(password, "Vin0DellaCas4");
        String oldPasswordEncoded = pass.encryptPassword(oldPassword, "Vin0DellaCas4");
        String o=pass.encryptPassword("Luciodalla", "Vin0DellaCas4");
        ManageToken mt = new ManageToken();

        if (mt.checkToken(token).getResponse() == "true") {
            if (typeClient.equals("client")) {
                myState = conn.getConnection().prepareStatement(queryUpdatePasswordClient);
            } else
                myState = conn.getConnection().prepareStatement(queryUpdatePasswordAdmin);

            myState.setString(1, newPassordEncoded);
            myState.setInt(2, token.getIdUser());
            myState.setString(3, oldPasswordEncoded);

            int rowAffected ;
            rowAffected = myState.executeUpdate();
            System.out.println(String.valueOf(rowAffected));

            //if the password insert is correct the row affected will produce a result if
            //not the password it's wrong
            if(rowAffected>0)
                return new Response("Password aggiornata con successo!");
            else return new Response("Password errata");
        }
        else return new Response(mt.checkToken(token).getResponse());


    }




    public LoginService() throws SQLException, ClassNotFoundException {
     }
}
