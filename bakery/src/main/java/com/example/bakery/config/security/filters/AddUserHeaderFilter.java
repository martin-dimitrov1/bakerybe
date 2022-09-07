package com.example.bakery.config.security.filters;

import com.example.bakery.models.AuthenticationUser;
import com.example.bakery.services.AuthenticationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AddUserHeaderFilter extends OncePerRequestFilter {

    private final AuthenticationService authService;

    @Autowired
    public AddUserHeaderFilter(AuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("b-auth");

        if (!StringUtils.isEmpty(tokenHeader)) {
            authService.findByToken(tokenHeader).ifPresentOrElse(
                    authUser -> {
                        SecurityContextHolder.getContext()
                                .setAuthentication(new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities()));
                    },
                    () -> {
                        AuthenticationUser anonUser = AuthenticationUser.createAnonymous(tokenHeader);
                        SecurityContextHolder.getContext()
                                .setAuthentication(new UsernamePasswordAuthenticationToken(anonUser, null, anonUser.getAuthorities()));
                    });
        } else {
//            var it = request.getHeaderNames().asIterator();
//
//            while (it.hasNext()) {
//                System.out.println(it.next());
//            }
        }


        filterChain.doFilter(request, response);
    }
}
