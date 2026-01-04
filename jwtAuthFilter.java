package com.example.learningSpring.configure;

import com.example.learningSpring.entity.user;
import com.example.learningSpring.service.customUserDetailService;
import com.example.learningSpring.service.jwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component

public class jwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private jwtService JwtSer;
    @Autowired
    private customUserDetailService userdetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            try{
                Claims claim=JwtSer.parse(token);
                String username=claim.getSubject();
                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails userdata=userdetailService.loadUserByUsername(username) ;
                    UsernamePasswordAuthenticationToken Authentication=new UsernamePasswordAuthenticationToken(
                            userdata,null,userdata.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(Authentication);

                }

            }

            catch(UsernameNotFoundException e){
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }
}
