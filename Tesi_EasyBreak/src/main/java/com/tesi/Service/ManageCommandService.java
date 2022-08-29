package com.tesi.Service;

import com.jdbcConnection;
import com.tesi.Entity.Command;
import com.tesi.Entity.Dish;
import com.tesi.Entity.Response;
import com.tesi.Entity.Token;
import com.tesi.Utility.ManageToken;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCommandService {

    public Response getCommands (Token token ,int idAdmin, int idClient, String status) throws SQLException, ClassNotFoundException {

        //i take all dishes associated to the admin trough the id of admin
        String queryGetCommands = "Select * from command where idAdmin = ? and idClient=? and status=? ORDER BY orderDate ASC;";
        String queryGetDishes = "select * from dish where id=? and idAdmin=? ";

        //jdbc instance
        jdbcConnection conn = new jdbcConnection().getInstance();

        //execute select query
        PreparedStatement myState = conn.getConnection().prepareStatement(queryGetCommands);
        PreparedStatement myState1 = conn.getConnection().prepareStatement(queryGetDishes);

        //arraylist declarations
        ArrayList<Command> commands = new ArrayList<>();
        ArrayList<Dish> dishes = new ArrayList<>();

        myState.setInt(1,idAdmin);
        myState.setInt(2,idClient);
        myState.setString(3,status);

        ResultSet myRes = myState.executeQuery();

        ManageToken mt = new ManageToken();
        //i create a list of dishes taked from db that i'll return in the get api in manageMenuController


        if(mt.checkToken(token).getResponse() == "true") {
            for (int i=0; i<commands.size(); i++) {
                myState1.setInt(1, commands.get(i).getIdDish());
                myState1.setInt(2,idAdmin);
                myRes= myState1.executeQuery();
                while (myRes.next()) {
                    dishes.add(new Dish(myRes.getString("dishName"), myRes.getInt("dishPrice"), myRes.getString("category")));
                }

            }
            return new Response(new ArrayList[]{dishes, commands});
        }
        else return mt.checkToken(token);

    }
}
