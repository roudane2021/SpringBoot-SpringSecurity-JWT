package ma.beit.springBootAndJwt.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.beit.springBootAndJwt.entity.User_;
import ma.beit.springBootAndJwt.util.JwtUtil;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;

    private static final String AUTHENTIFICATION = "Authorization";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,JwtUtil jwtTokenUtil,UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User_ appUser= new ObjectMapper().readValue(request.getInputStream(),User_.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getName(),appUser.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user=(User)authResult.getPrincipal();
        List<String> roles=new ArrayList<>();
        authResult.getAuthorities().forEach(a->{
            roles.add(a.getAuthority());
        });
        final UserDetails userDetails = userDetailsService
				.loadUserByUsername(user.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(AUTHENTIFICATION,jwt);
    }

}
