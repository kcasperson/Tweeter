package edu.byu.cs.tweeter.model.domain;

import java.io.Serializable;

/**
 * Represents an auth token in the system.
 */
public class AuthToken implements Serializable {
    private boolean good = true;

    public boolean isGood() {
        return good;
    }

    public void decommission() {
        good = false;
    }
}
