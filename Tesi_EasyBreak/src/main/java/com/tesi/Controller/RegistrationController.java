package com.tesi.Controller;

import com.tesi.Entity.Response;
import com.tesi.Entity.User;
import com.tesi.Service.RegistrationService;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;


@RestController
@RequestMapping("Registrazione")
public class RegistrationController {
    RegistrationService reg = new RegistrationService();

    @PostMapping
    public Response saveUser(@RequestBody User user,  HttpServletResponse response) throws SQLException, ClassNotFoundException {

       //i check if in the request body i have all the params required
       if(user.getName() == null || user.getSurname()== null || user.getPassword()== null || user.getEmail()== null ) {
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
           return new Response("Mancano i parametri richiesti", "");
       }
       else return reg.createNewUser(user);
    }


    public RegistrationController() throws SQLException, ClassNotFoundException {
    }

}
