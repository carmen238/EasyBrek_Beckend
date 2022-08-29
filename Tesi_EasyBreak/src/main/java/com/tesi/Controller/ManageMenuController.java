package com.tesi.Controller;

import com.tesi.Entity.Dish;
import com.tesi.Entity.Response;
import com.tesi.Entity.Token;
import com.tesi.Service.ManageMenuService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class ManageMenuController {
    @RequestMapping("Dishes")
    @GetMapping
    public Response getDishes(@RequestHeader("token") String token, @RequestHeader("id") int id) throws SQLException, ClassNotFoundException, ParseException {
        ManageMenuService mms = new ManageMenuService();
        Token tok = new Token(token, id);
        return new Response(mms.getDishes(tok));
    }


    //method to return the dishes of client or admin that he selected(i handle the dishes in session)
    @RequestMapping("ListDishes")
    @PostMapping
    public Response listDishes(@RequestHeader("token") String token, @RequestHeader("status") String status,@RequestHeader("idadmin") int idAdmin,@RequestHeader("idclient") int idClient, @RequestHeader("typeUser") String typeUser, @RequestBody ArrayList<Dish> dishes, HttpServletResponse response, HttpServletRequest request) throws SQLException, ClassNotFoundException, IOException, ServletException {
        ManageMenuService mms = new ManageMenuService();
        return mms.getListDishes(request, response,dishes, status,typeUser, idAdmin, idClient);
    }



    public ManageMenuController() throws SQLException, ClassNotFoundException {
    }

}