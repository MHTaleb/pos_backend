/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos;

        import com.easyData.pos.easyPos.rest.model.Role;
        import com.easyData.pos.easyPos.service.UserService;
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
         * Ceci est la classe de configuration de securité principal
         *
         * @author taleb
         */
        @Configuration
        @EnableWebSecurity
        @EnableGlobalMethodSecurity(securedEnabled = true)
        public class ServerConfig extends WebSecurityConfigurerAdapter {

            @Autowired
            UserService userDetailsService;

            /**
             * encodeur Base 64 du mot de passe (necessaire dans ssl/https)
             *
             * @return
             */
            @Bean
            public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
            }

            @Autowired
            private SpringAuthenticationSuccessHandler authenticationSuccessHandler;

            @Autowired
            private AccessDeniedHandler accessDeniedHandler;

            /**
             * ici on arme le service d authentification qu on creer
             *
             * @param auth les cridential d authetification
             * @throws Exception
             */
            @Override
            protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
            }

            private static final String[] AUTH_WHITELIST = {

            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };
            
            /**
             * La methode configure est la logique de securité de notre serveur
             * <b> on y indique le point d entrer de l authentification le point de
             * sortie ainsi les regle sur les liens et la gestion du cache ainsi la
             * security csr est desactivé vu que notre client est une application pour l
             * instant pas un navigateur</b>
             * pour un navigateur il faudrai une autre configuration sur un autre port
             * (un duplica du serveur avec csr active )
             *
             * @param http le wrapper du protocol http il contien les information telque
             * les cookies et le lien demander ect
             * @throws Exception
             */
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
                        .antMatchers("/logins/login").permitAll()
                        .antMatchers("/logins/logout").permitAll()
                        .antMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .requestCache()
                        .requestCache(new NullRequestCache())
                        .and().httpBasic();
                http
                        .csrf().disable();

                System.out.println(" 1 : " + Role.USER.name() + "   ---   " + Role.USER.toString());
            }

            /**
             * cette classe est une configuration du message d erreur 401, necessaire
             * pour le fonctionement de l authetification via bdd
             */
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

            /**
             * cette classe est la configuration qui permet a spring de lire et
             * identifier les utilisateurs depuis la base de données
             */
            public class AppAuthProvider extends DaoAuthenticationProvider {

                @Autowired
                UserService userDetailsService;

                /**
                 * C'est la methode a reimplementer pour se servir du service d
                 * authentification local qui est
                 * <b>UserDetails</b> ce service nous permet de lire la table users dans
                 * la bdd et de verifier avec
                 * <b>authentication</b> object pourplus de detail consulter bealdung
                 * how to use sql user database for auth
                 *
                 * @param authentication ceci est le wrapper de spring boot qui contien
                 * les cridentials
                 * @return un bean d authetification consomable de la par de spring
                 * security
                 * @throws AuthenticationException
                 */
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

                /**
                 * ajouter un mechanisme de validation a l authentification efectuer
                 *
                 * @param authentication
                 * @return vrai vu aucune implementation n est envisager pour le moment
                 */
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

            /**
             *
             * @return
             */
            @Bean
            public AuthenticationProvider getProvider() {
                AppAuthProvider provider = new AppAuthProvider();
                provider.setUserDetailsService(userDetailsService);
                return provider;
            }

        }
