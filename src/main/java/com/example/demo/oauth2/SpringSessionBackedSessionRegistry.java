/*
package com.example.demo.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;


@Component
public class SpringSessionBackedSessionRegistry implements SessionRegistry {

    private FindByIndexNameSessionRepository<? extends ExpiringSession> sessionRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public SpringSessionBackedSessionRegistry(FindByIndexNameSessionRepository<? extends ExpiringSession> sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Object> getAllPrincipals() {
        throw new UnsupportedOperationException("SpringSessionBackedSessionRegistry does not support retrieving all principals, since Spring Session provides no way to obtain that information");
    }

    @Override
    public List<SessionInformation> getAllSessions(Object principal, boolean includeExpiredSessions) {
        return sessionRepository
                .findByIndexNameAndIndexValue(PRINCIPAL_NAME_INDEX_NAME, name(principal))
                .values()
                .stream()
                .filter(session -> includeExpiredSessions || !session.isExpired())
                .map(session -> new SpringSessionBackedSessionInformation(session, sessionRepository))
                .collect(toList());
    }

    @Override
    public SessionInformation getSessionInformation(String sessionId) {
        ExpiringSession session = sessionRepository.getSession(sessionId);
        if (session != null) {
            return new SpringSessionBackedSessionInformation(session, sessionRepository);
        }
        return null;
    }

    */
/**
     * This is a no-op, as we don't administer sessions ourselves.
     *//*

    @Override
    public void refreshLastRequest(String sessionId) {
    }

    */
/**
     * This is a no-op, as we don't administer sessions ourselves.
     *//*

    @Override
    public void registerNewSession(String sessionId, Object principal) {
    }

    */
/**
     * This is a no-op, as we don't administer sessions ourselves.
     *//*

    @Override
    public void removeSessionInformation(String sessionId) {
    }

    */
/**
     * Derives a String name for the given principal.
     *//*

    private String name(Object principal) {
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        }
        return principal.toString();
    }
}*/
