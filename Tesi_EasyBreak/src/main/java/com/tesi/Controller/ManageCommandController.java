package com.tesi.Controller;

import com.tesi.Entity.Response;
import com.tesi.Entity.Token;
import com.tesi.Service.ManageCommandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.text.ParseException;

public class ManageCommandController {

    @RequestMapping("Commands")
    @GetMapping
    public Response getCommands(@RequestHeader("token") String token, @RequestHeader("idAdmin") int idAdmin, @RequestHeader("idClient") int idClient, @RequestHeader("status") String status) throws SQLException, ClassNotFoundException, ParseException {
        ManageCommandService mcs = new ManageCommandService();
        Token tok = new Token(token, idAdmin);
        return new Response(mcs.getCommands(tok, idAdmin,idClient, status));
    }
}
