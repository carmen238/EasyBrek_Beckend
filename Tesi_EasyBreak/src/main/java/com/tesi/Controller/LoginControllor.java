package com.tesi.Controller;

import com.tesi.Entity.Login;
import com.tesi.Entity.Response;
import com.tesi.Entity.Token;
import com.tesi.Entity.User;
import com.tesi.Service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@RestController

    public class LoginControllor {
        LoginService check = new LoginService();


        public LoginControllor() throws SQLException, ClassNotFoundException {
        }

        //method to check the credentials
        @RequestMapping("Login")
        @PostMapping
        public Response checkCredentials(@RequestBody Login logCredentials,  HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {

            if(logCredentials.getEmail() == null ||logCredentials.getPassword() == null ){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return new Response("Mancano i parametri richiesti", "");
            }
            else if(logCredentials.getEmail() == "" ||logCredentials.getPassword() == ""){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return new Response("Email o password non conformi", "");}
            else{
                if(check.checkUser(logCredentials.getEmail(), logCredentials.getPassword(), logCredentials.getTypeUser()).getError() == "Utente inesistente")
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return check.checkUser(logCredentials.getEmail(), logCredentials.getPassword(), logCredentials.getTypeUser());
            }



        }

         //method that return the info about user
         @RequestMapping("User")
         @GetMapping
         public Response getUser(@RequestHeader("token") String token, @RequestHeader("id") int id, @RequestHeader("typeuser") String typeUser) throws SQLException, ClassNotFoundException, ParseException {
          LoginService ls = new LoginService();
          Token tok = new Token(token, id);
           return new Response(ls.getUser(tok, typeUser));
         }


         //method to update tha email
          @RequestMapping("ChangeEmail")
          @PostMapping
          public Response changeEmail(@RequestHeader("token") String token, @RequestHeader("id") int id, @RequestHeader("typeUser") String typeUser,@RequestBody String newEmail,  HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
              Token tok = new Token(token, id);

              return check.changeEmail(tok,newEmail, typeUser);
          }


          //method to update tha email
          @RequestMapping("ChangePassword")
          @PostMapping
          public Response changePassword(@RequestHeader("token") String token, @RequestHeader("id") int id, @RequestHeader("typeUser") String typeUser,@RequestBody User u, HttpServletResponse response)
                  throws SQLException, ClassNotFoundException, IOException {
             Token tok = new Token(token, id, typeUser);
           return check.changePassword(tok, u.getOldPassword(),u.getPassword());
    }


}
