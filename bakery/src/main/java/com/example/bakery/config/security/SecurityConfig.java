package com.example.bakery.config.security;

import com.example.bakery.config.security.filters.AddUserHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AddUserHeaderFilter addUserHeaderFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, AddUserHeaderFilter addUserHeaderFilter) {
        this.userDetailsService = userDetailsService;
        this.addUserHeaderFilter = addUserHeaderFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
//                .anonymous(this::loadAnonymousUser)
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/user/generateToken").permitAll()
//                .antMatchers("/cart/**").permitAll()
//                .antMatchers("/product/**").hasAuthority("ADMIN")
//                .antMatchers("/order/**").hasAuthority("ADMIN")
//                .antMatchers("/custom/**").hasAnyAuthority("GUEST")
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated()
                .anyRequest().permitAll()

                .and()
                .addFilterAfter(addUserHeaderFilter, SecurityContextPersistenceFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public UserDetailsService userDetailsServiceBean() {
        return this.userDetailsService;
    }
}
