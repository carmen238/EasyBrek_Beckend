package com.tesi.Service;


import com.jdbcConnection;
import com.tesi.Entity.Response;
import com.tesi.Entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationService {

    public Response createNewUser(User u) throws SQLException, ClassNotFoundException {
        //Query to check if another user use this email, query to add new user
        String queryCheck = "Select * from client where email = ?";
        String queryCheck1 = "Select * from admin where email = ?";
        String queryCheckCompany = "Select id from admin where companyName = ?";
        String queryInsert = "insert into client(name,surname, email, password,adminId,enabled ) values(?, ?, ?, ?, ?, ?)";
        String queryInsert1 = "insert into admin(name,surname, email, password,companyName, enabled) values(?, ?, ?, ?, ?, ?)";

        //jdbc instance
        jdbcConnection conn = jdbcConnection.getInstance();

        //execute select query
        PreparedStatement myState;
        if(u.getTypeUser().equals("admin"))
            myState = conn.getConnection().prepareStatement(queryCheck1);
        else
            myState = conn.getConnection().prepareStatement(queryCheck);

        myState.setString(1, u.getEmail());
        ResultSet myRes = myState.executeQuery();

        //encode password to insert it in db crypted
        PasswordService pass = new PasswordService();
        String passordEncoded= pass.encryptPassword(u.getPassword(), "Vin0DellaCas4");
        u.setPassword(passordEncoded);

        //if myRes.next() != null there's user that use this email so i set the error in the
        // response object with message
        //else i add new user
        if(myRes.next())
            return  new Response("Email già utilizzata","");
        else {
            //if the user is type client i check if the name of company exists
            //if exists i execute a query to get the id of admin of the company
            //to associate the entity  client to admin (the admin can have 0 or more client)
            //i insert the new user
            if(u.getTypeUser().equals("client")){
                PreparedStatement myState3= conn.getConnection().prepareStatement(queryCheckCompany);
                myState3.setString(1,u.getCompanyName());
                ResultSet myRes3 = myState3.executeQuery();
                if(myRes3.next()){
                    PreparedStatement myState4= conn.getConnection().prepareStatement(queryInsert);
                    int adminId = myRes3.getInt("id");
                    u.setEnabled(true);
                    myState4.setString(1, u.getName());
                    myState4.setString(2, u.getSurname());
                    myState4.setString(3, u.getEmail());
                    myState4.setString(4, u.getPassword());
                    myState4.setInt(5, adminId);
                    myState4.setBoolean(6, false);
                    myState4.executeUpdate();
                    return  new Response("","Utente registrato");
                }

                else return new Response("Nome della compagnia non trovato!","");
            }
            else {
                //if user is admin
                //I pass the params receveid to the query to add the new user into db
                PreparedStatement myState2 = conn.getConnection().prepareStatement(queryInsert1);
                u.setEnabled(true);
                myState2.setString(1, u.getName());
                myState2.setString(2, u.getSurname());
                myState2.setString(3, u.getEmail());
                myState2.setString(4, u.getPassword());
                myState2.setString(5, u.getCompanyName());
                myState2.setBoolean(6, true);
                myState2.executeUpdate();
                return new Response("", "Utente registrato");
            }

        }

    }



    public RegistrationService() throws SQLException, ClassNotFoundException {
    }
}
