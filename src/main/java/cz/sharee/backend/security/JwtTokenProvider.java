package cz.sharee.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class JwtTokenProvider {

    @Value("@{jwt.secret}")
    private String secret;

    public String generateJwtToken(UserDetails userDetails){
        String[] authorities = getAuthoritiesFromUserDetails(userDetails);

        var token = JWT.create()
                .withIssuer(SecurityConstants.PROVIDER)
                .withAudience(SecurityConstants.ADMINISTRATION)
                .withIssuedAt(new Date())
                .withSubject(userDetails.getUsername())
                .withArrayClaim(SecurityConstants.AUTHORITIES, authorities)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(secret.getBytes()));
        return token;
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(String token){
        JWTVerifier verifier = getJwtVerifier();
        // decode the JWT token
        DecodedJWT decodedJWT = verifier.verify(token);
        // get raw string claims from the decoded token
        String[] claims = decodedJWT.getClaim(SecurityConstants.AUTHORITIES).asArray(String.class);
        // convert raw claims to proper authorities
        List<GrantedAuthority> authorities = stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
        // do something unknown in Security context
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return token;
    }

    public String getSubject(String token){
        JWTVerifier jwtVerifier = getJwtVerifier();
        // already decoded JWTToken
        String subject = jwtVerifier.verify(token).getSubject();
        return subject;
    }

    public boolean isTokenValid(String username, String token){
        return StringUtils.isNotBlank(username) && isTokenNotExpired(token);
    }

    // helper methods

    private String[] getAuthoritiesFromUserDetails(UserDetails userDetails){
        List<String> claims = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return claims.toArray(new String[claims.size()]);
    }

    private boolean isTokenNotExpired(String token){
        JWTVerifier verifier = getJwtVerifier();
        Date expireDate = verifier.verify(token).getExpiresAt();
        return expireDate.after(new Date(System.currentTimeMillis()));
    }


    private JWTVerifier getJwtVerifier() throws JWTVerificationException{
        JWTVerifier verifier;
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            verifier = JWT.require(algorithm).withIssuer(SecurityConstants.PROVIDER).build();
        }catch (JWTVerificationException jwtVerificationException){
            throw new JWTVerificationException(SecurityConstants.TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }
}

