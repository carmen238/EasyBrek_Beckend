package com.tesi.Utility;

import com.tesi.Entity.Response;
import com.tesi.Entity.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

public class ManageToken {
    long now = System.currentTimeMillis();
    Date nowDate = Date.from(Instant.ofEpochSecond(now));
    Date expirationDate =Date.from(Instant.ofEpochSecond(now).plus(3000, ChronoUnit.SECONDS));
    public Token generateToken(String password, String email, String secretKey, int idUser){
        String jwtToken = Jwts.builder()
                .claim("password", password)
                .claim("email", email)
                .claim("key", secretKey)
                .setSubject(secretKey)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(nowDate)
                .setExpiration(expirationDate)
                .compact();
        Token token = new Token(jwtToken, expirationDate, idUser);

        return token;
    }

    private String SECRET_KEY = "secret";


    public String extractKey(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
    }
    public Response checkToken(Token token){
        long expDate = extractExpiration(token.getToken()).getTime();
        if(!(extractKey(token.getToken()).equals("Vin0DellaCas4" )))
            return  new Response("Utente non autorizzato","");
        if(expDate-now <= 0)
            return  new Response( "Token scaduto", "");
        else
            return  new Response("", "true");
    }


}
