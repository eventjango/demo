/*
package com.example.demo.oauth2;


import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.session.ExpiringSession;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

import java.util.Date;

import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;


public class SpringSessionBackedSessionInformation extends SessionInformation {


    private SessionRepository<? extends ExpiringSession> sessionRepository;

    private static String resolvePrincipal(Session session) {
        String principalName = session.getAttribute(PRINCIPAL_NAME_INDEX_NAME);
        if (principalName != null) {
            return principalName;
        }
        SecurityContext securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContext != null && securityContext.getAuthentication() != null) {
            return securityContext.getAuthentication().getName();
        }
        return "";
    }

    public SpringSessionBackedSessionInformation(Object principal, String sessionId, Date lastRequest) {
        super(principal, sessionId, lastRequest);
    }
}
*/
