package com.valdir.poolart.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valdir.poolart.domain.dto.CredentialsDTO;
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

/**
 * Quando eu crio uma classe que extende
 * UsernamePasswordAuthenticationFilter automaticamente
 * o spring vai entender que esse filtro vai interceptar
 * a requisição POST no endpoint /login que inclusive
 * é o um endpoint reservado do Spring Security
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * AuthenticationManager é a principal interface de
     * estratégia para autenticação.
     * Se o principal da autenticação de entrada for válido e verificado,
     * o metodo authenticate retorna uma instância de Authentication com
     * o sinalizador de autenticado definido como verdadeiro. Do contrário,
     * se o principal não for válido, ele lançará uma AuthenticationException.
     * Para o último caso, ele retorna nulo se não puder decidir.
     */
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Metodo que irá tentar autenticar
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        /*
          Vamos pegar os valores passados na requisição POST para
          o endpoint /login, convertê-los em CredentialsDTO
          instanciar um objeto do tipo UsernamePasswordAuthenticationToken
          passá-lo como parâmetro para o metodo authenticate que irá
          tentar realizar a autenticação. O framework fará isso usando
          os contratos implementados em UserDetails, UserDetailsService
          de forma automática
         */
        try {
            /* getInputStream() Recupera o corpo da solicitação como dados
               binários usando um ServletInputStream. Este método ou getReader
               pode ser chamado para ler o corpo */
            CredentialsDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);

            /* Criando esse objeto para passar para o metodo autenticate do authenticationManager
               verificar se o usuario e senha passados na requisição são válidos */
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>());

            /* Esse é o metodo que verifica se o usuario e senha passados são válidos */
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Caso a tentativa de autenticação ocorrer com
     * sucesso esse metodo será chamado
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {

        String username = ((UserSS) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * Esse metodo irá ser chamado caso não ocorra a autenticação
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().append(json());

    }

    /**
     * Metodo onde escrevemos um json para retornar como erro
     * de credenciais para o usuario que digitar email e senha
     * incorretos na autenticação. Esse metodo será usado pelo
     * metodo unsuccessfulAuthentication acima.
     */
    private String json() {
        long date = new Date().getTime();
        return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
    }
}