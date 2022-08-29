package com.tesi.Service;
import com.jdbcConnection;
import com.tesi.Entity.Dish;
import com.tesi.Entity.Response;
import com.tesi.Entity.Token;
import com.tesi.Utility.ManageToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ManageMenuService {
    public Response getDishes (Token token) throws SQLException, ClassNotFoundException {

        //i take all dishes associated to the admin trough the id of admin
        String queryGetDishes = "Select * from dish where idAdmin = ?";
        //jdbc instance
        jdbcConnection conn = new jdbcConnection().getInstance();
        //execute select query
        PreparedStatement myState = conn.getConnection().prepareStatement(queryGetDishes);
        myState.setInt(1,token.getIdUser());
        ResultSet myRes = myState.executeQuery();
        ManageToken mt = new ManageToken();
        //i create a list of dishes taked from db that i'll return in the get api in manageMenuController
        ArrayList<Dish> dishes = new ArrayList<>();

        if(mt.checkToken(token).getResponse() == "true") {
            while (myRes.next()) {
                dishes.add(new Dish(myRes.getString("dishName"), myRes.getString("category"), myRes.getInt("dishPrice"), myRes.getInt("id"), 1));
            }
            return new Response(dishes);
        }
        else return mt.checkToken(token);

    }


    public Response getListDishes(HttpServletRequest request, HttpServletResponse response, ArrayList<Dish> dishes, String status,String userType, int idAdmin, int idClient)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();

        String insertDishesQuery = "insert into dish(dishName,dishPrice,category, idAdmin) values(?,?,?,?)";
        String removeDish = "Delete from dish where id = ?";

        String takeMajorId= "select max(idCommand) from command where idAdmin = ?";
        String insertNewOrder= "insert into command(idCommand,orderDate,status,idDish,idClient,idAdmin,dishesNumber) values(?,?,?,?,?,?,?)";


        //jdbc instance
        jdbcConnection conn = jdbcConnection.getInstance();
        //execute select query
        PreparedStatement myState;
        PreparedStatement myState1;

        int rowAffected ;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String today= dtf.format(now);

        //i use this switch to handling the case in wich admin or client select new dish and trough the session i can save
        //the data empty (the user didn't select anything ), it mean the user selected the dish but i did'nt press "CONFERMA" (first case)
        //second case he pressed "CONFERMA" so i add a new order if the user is a client or a new dish if the user is admin
        //and after i set the dishes session whit an empty array because the dish o the order were added
        //third case i set the dishes session with the data that i pass in apicall
        switch (status){
              case "E":
                  request.getSession().setAttribute("dishes", dishes);
                  break;
              case "C": {
                  if(!userType.equals("client")){

                      myState= conn.getConnection().prepareStatement(insertDishesQuery);
                      myState1= conn.getConnection().prepareStatement(removeDish);

                    for(int i=0; i< dishes.size(); i++) {
                      myState1.setInt(1, dishes.get(i).getId());
                      rowAffected = myState1.executeUpdate();

                      //the admin can change his menu , add and remove the dishes
                      //so if row effetcted >0 means that this dish exists and is a dish tha the user wanna remove so i drop from list
                      //else he wanna add a new dish and i add it in the list
                      if (rowAffected < 1) {
                          myState.setString(1, dishes.get(i).getDishName());
                          myState.setInt(2, dishes.get(i).getDishPrice());
                          myState.setString(3, dishes.get(i).getCategory());
                          myState.setInt(4, idAdmin);
                          rowAffected = myState.executeUpdate();
                      }

                    }

                  } else{
                      myState= conn.getConnection().prepareStatement(takeMajorId);
                      myState1= conn.getConnection().prepareStatement(insertNewOrder);

                      myState.setInt(1,  19);
                      ResultSet myRes = myState.executeQuery();
                      int commandId;
                      if(myRes.next()) {
                          commandId = myRes.getInt("max(idCommand)")+1 ;
                      }
                      else commandId= 1;

                      for(int i=0; i< dishes.size(); i++) {

                          //the admin can change his menu , add and remove the dishes
                          //so if row effetcted >0 means that this dish exists and is a dish tha the user wanna remove so i drop from list
                          //else he wanna add a new dish and i add it in the list
                          myState1.setInt(1, commandId);
                          myState1.setString(2, today);
                          myState1.setString(3, "inCorso");
                          myState1.setInt(4, dishes.get(i).getId());
                          myState1.setInt(5, idClient);
                          myState1.setInt(6, idAdmin);
                          myState1.setInt(7, dishes.get(i).getDishesNumber());

                          myState1.executeUpdate();


                      }

                  }

                  ArrayList<Dish> emptyArray = new ArrayList<>();

                  request.getSession().setAttribute("dishes", emptyArray);
              }
              break;

               default:
                   if (dishes.size() > 0) request.getSession().setAttribute("dishes", dishes);

                  break;


          }

            // work with the session...

        return new Response( (ArrayList<Dish>) request.getSession().getAttribute("dishes"));
    }

}
