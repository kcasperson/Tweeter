package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

import java.util.Objects;

public class SignoutRequest {
    private User user;
    private AuthToken authToken;

    private SignoutRequest() {}

    public SignoutRequest(User user, AuthToken authToken) {
        this.user = user;
        this.authToken = authToken;
    }

    public User getUser() {
        return user;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignoutRequest)) return false;
        SignoutRequest that = (SignoutRequest) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getAuthToken(), that.getAuthToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getAuthToken());
    }
}
