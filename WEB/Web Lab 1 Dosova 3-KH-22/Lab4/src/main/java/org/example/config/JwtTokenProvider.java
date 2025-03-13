package org.example.config;

import org.example.model.AccountEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expired}")
    private long validityInMilliseconds;

    public String createToken(AccountEntity account) {
        Claims claims = Jwts.claims().setSubject(account.getLogin());
        claims.put("roles", buildRoles(account));
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private List<String> buildRoles(AccountEntity accountEntity) {
        List<String> roles = new ArrayList<>();

        if (accountEntity != null && accountEntity.getRoles() != null) {
            accountEntity.getRoles().forEach(roleEntity ->
                    roles.add(roleEntity.getRole()));
        }

        return roles;
    }

}
