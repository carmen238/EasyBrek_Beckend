package com.tesi;

import com.tesi.Utility.ManageToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


@SpringBootApplication()
public class TesiEasyBreakApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		SpringApplication.run(TesiEasyBreakApplication.class, args);


	}

}
