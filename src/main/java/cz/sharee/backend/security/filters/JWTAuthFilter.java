package cz.sharee.backend.security.filters;

import cz.sharee.backend.security.JwtTokenProvider;
import cz.sharee.backend.security.SecurityConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // if options http method, let it go
        if(httpServletRequest.getMethod().equalsIgnoreCase(SecurityConstants.OPTIONS_HTTP_METHOD)) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
        }else{
            // get jwt token from authorization header
            String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            // token is not present / does not have prefix
            if(authHeader == null || !authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }else{
                log.debug("TOken exists");
                // extract the token
                String token = authHeader.substring(SecurityConstants.TOKEN_PREFIX.length());
                // get username, will fail if the token is bullshit
                String username = tokenProvider.getSubject(token);
                if(tokenProvider.isTokenValid(username, token) && SecurityContextHolder.getContext().getAuthentication() == null){
                    // token is correct
                    // get authorities, needed to set Authentication properly
                    List<GrantedAuthority> authorities = tokenProvider.getAuthoritiesFromToken(token);
                    Authentication authentication = tokenProvider.getAuthentication(username, authorities, httpServletRequest);
                    // set authentication token
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else{
                    SecurityContextHolder.clearContext();
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
