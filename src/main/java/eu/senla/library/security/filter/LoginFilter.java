package eu.senla.library.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.model.Credential;
import eu.senla.library.security.JwtProvider;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;

    public LoginFilter(JwtProvider jwtProvider,ObjectMapper objectMapper,AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        this.jwtProvider = jwtProvider;
    }

   @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response){
        return super.requiresAuthentication(request,response);
   }

   @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
       Credentials credentials=objectMapper.readValue(request.getInputStream(),Credentials.class);
       return getAuthenticationManager().authenticate(
               new UsernamePasswordAuthenticationToken(
                       credentials.username,
                       credentials.password
               )
       );
   }

   @Override
   protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,Authentication authResult){
        String token= prepareJwt(authResult);
        response.addHeader(HttpHeaders.AUTHORIZATION,token);
   }

private String prepareJwt(Authentication authResult){
        return jwtProvider.buildToken(authResult.getName());
}


   @Data
    static class Credentials{
        private String username;
        private String password;
   }
}
