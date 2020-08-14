package com.javafullstackcourse.noticessessiontaskalicia.Utilities;

import java.util.HashSet;

public class SessionKeeper {
    private static SessionKeeper sessionKeeperInstance = null;

    private HashSet<String> validSessions = new HashSet<>();

    public boolean checkSession(String session) {
        return validSessions.contains(session);
    }

    public void addSession(String session) {
        validSessions.add(session);
    }

    public void removeSession(String session) {
        if (validSessions.contains(session)) {
            validSessions.remove(session);
        }
    }

    public static SessionKeeper getInstance() {
        if (sessionKeeperInstance == null) {
            sessionKeeperInstance = new SessionKeeper();
        }

        return sessionKeeperInstance;
    }
}
