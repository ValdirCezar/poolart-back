package com.valdir.poolart.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valdir.poolart.domain.dto.CredentialsDTO;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    /**
     * Metho will attempt authenticate
     *
     * @return Authentication
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        log.info("::: JWT_AUTHENTICATION_FILTER - Attempting authentication");
        CredentialsDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>());

        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * On successful authentication the token will be added on header response
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {
        log.info("::: JWT_AUTHENTICATION_FILTER - Successfull authentication");
        String username = ((UserSS) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.setHeader("access-control-expose-headers", "Authorization");
        response.setHeader("Authorization", "Bearer " + token);
    }

    /**
     * On unsuccessful authentication an error will be released
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        log.error("::: JWT_AUTHENTICATION_FILTER - Unsuccessfull authentication");
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().append(json());
    }

    /**
     * Method to response on unsuccessful authentication case
     */
    private CharSequence json() {
        log.info("::: JWT_AUTHENTICATION_FILTER - Generating json");
        long date = new Date().getTime();
        return "{"
                + "\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Bad Credencials\", "
                + "\"message\": \"Incorrect email or password\", "
                + "\"path\": \"/login\"}";
    }

}
