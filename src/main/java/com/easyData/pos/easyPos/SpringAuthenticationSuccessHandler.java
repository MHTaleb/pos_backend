/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * An authentication success strategy which can make use of the
 * DefaultSavedRequest which may have been stored in the session by the
 * ExceptionTranslationFilter. When such a request is intercepted and requires
 * authentication, the request data is stored to record the original destination
 * before the authentication process commenced, and to allow the request to be
 * reconstructed when a redirect to the same URL occurs. This class is
 * responsible for performing the redirect to the original URL if appropriate.
 * 
 * https://docs.spring.io/spring-security/site/docs/4.2.4.RELEASE/apidocs/org/springframework/security/web/authentication/SavedRequestAwareAuthenticationSuccessHandler.html
 *
 * @author taleb
 */
@Component
public class SpringAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String ajax = request.getParameter("ajax");

        String username = authentication.getName();

        System.out.println("User: " + username);

        if (ajax != null && ajax.equalsIgnoreCase("true")) {
            CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                    .getName());
            response.getWriter().println("APP-AJAX-LOGIN-SUCCESS;" + csrf.getToken());
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
