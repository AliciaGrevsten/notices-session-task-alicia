package com.javafullstackcourse.noticessessiontaskalicia.Utilities;

import com.javafullstackcourse.noticessessiontaskalicia.Models.AppUser;

import java.util.HashSet;

public class SessionKeeper {
    private static SessionKeeper sessionKeeperInstance = null;

    private HashSet<String> validSessions = new HashSet<>();
    private AppUser userSession = new AppUser();

    public boolean checkSession(String session) {
        return validSessions.contains(session);
    }

    public void addSession(String session) {
        validSessions.add(session);
    }

    public void addUserSession(AppUser user) { userSession = user; }

    public AppUser getUserSession() {
        return userSession;
    }

    public void removeSession(String session) {
        if (validSessions.contains(session)) {
            validSessions.remove(session);
        }
    }

    public void removeUserSession(AppUser user) {
        if (userSession.equals(user)) {
            userSession = null;
        }
    }

    public static SessionKeeper getInstance() {
        if (sessionKeeperInstance == null) {
            sessionKeeperInstance = new SessionKeeper();
        }

        return sessionKeeperInstance;
    }
}
