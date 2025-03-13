package com.school.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
@Component
public class    JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    public UserDetails parseUserDetails(String token) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                ArrayList<?> roles = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody()
                        .get("roles", ArrayList.class);
                if (roles != null) {
                    for (Object role : roles) {
                        if (role instanceof String) {
                            grantedAuthorities.add(
                                    new SimpleGrantedAuthority("ROLE_" + ((String) role).toUpperCase()));
                        }
                    }
                }
                return grantedAuthorities;
            }

            @Override
            public String getPassword() {
                return "";
            }

            @Override
            public String getUsername() {
                return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

    public void validateToken(String token) throws ExpiredJwtException {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        claims.getBody().getExpiration().before(new Date());
    }
}
