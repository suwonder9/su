package com.wonder.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {

    private final static String userName = "userName";
    private final static String Id = "id";
    private final static String SECRET = "su_secret";


    public static String generateToken(UserDetails userDetails,Long expiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(userName, userDetails.getUsername());
        claims.put(Id, new Date());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    private Claims getClaimsFromToken(String token) throws ExpiredJwtException {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException exp){
            throw exp;
        }catch (Exception e) {
            log.error("parse claims from token [{}] failed,exception cause [{}]",token,e.getMessage());
            claims = null;
        }
        return claims;
    }

    public boolean validateToken(String token){
        boolean res = false;
        if (StringUtils.isBlank(token)){
            log.error("token [{}] format error.",token);
            return res;
        }
        return (!isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        boolean res = false;
        try {
            final Date expiration = getExpirationDateFromToken(token);
            if (expiration == null)
                res = true;
            res = expiration.before(new Date());
        }catch (ExpiredJwtException e){
            res = true;
            log.error("token [{}] expired.",token);
        }
        return res;
    }

    public Date getExpirationDateFromToken(String token) throws ExpiredJwtException {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims != null)
                expiration = claims.getExpiration();
            else
                expiration = null;
        } catch (ExpiredJwtException exp){
            throw exp;
        }
        return expiration;
    }


}
