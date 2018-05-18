/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos;

import com.easyData.pos.easyPos.rest.model.Role;
import com.easyData.pos.easyPos.service.MyUserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;

/**
 *
 * @author taleb
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ServerConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SpringAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("App header"))
                .and()
                .authenticationProvider(getProvider())
                .formLogin()
                .loginProcessingUrl("/logins/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and().httpBasic();

        http.logout()
                .logoutUrl("/logins/logout")
                .logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .and().httpBasic();
        http.authorizeRequests()
                //.antMatchers("/docs").hasAnyRole(Role.USER.name(), Role.ADMIN.toString())
                //.antMatchers("/acl").hasAnyRole(Role.ADMIN.toString())
                .antMatchers("/logins/login").permitAll()
                .antMatchers("/logins/logout").permitAll()
                .anyRequest().authenticated().and()
                .requestCache()
                .requestCache(new NullRequestCache())
                .and().httpBasic();
        http
                .csrf().disable();
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        System.out.println(" 1 : " + Role.USER.name() + "   ---   " + Role.USER.toString());
    }

    private static class Http401AuthenticationEntryPoint implements AuthenticationEntryPoint {

        private final String authenticateHeader;

        public Http401AuthenticationEntryPoint(String authenticateHeader) {
            this.authenticateHeader = authenticateHeader;
        }

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                AuthenticationException authException) throws IOException, ServletException {

            response.setHeader("WWW-Authenticate", authenticateHeader);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    authException.getMessage());
        }
    }

    public class AppAuthProvider extends DaoAuthenticationProvider {

        @Autowired
        MyUserService userDetailsService;

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
            String name = auth.getName();
            String password = auth.getCredentials()
                    .toString();
            UserDetails user = userDetailsService.loadUserByUsername(name);
            if (user == null) {
                throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
            }
            return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return true;
        }
    }

    
    
    private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Bean
    public AuthenticationProvider getProvider() {
        AppAuthProvider provider = new AppAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

}
