package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class SignupResponse extends LoginResponse{

    private User user;
    private AuthToken authToken = null;

    public SignupResponse(String message) {
        super(message);
    }

    public SignupResponse(User user, AuthToken authToken) {
        super(user, authToken);
        this.user = user;
        this.authToken = authToken;
    }

    public boolean isSuccess() {
        return super.isSuccess();
    }

    public User getUser() {
        return user;
    }

    /**
     * Returns the auth token.
     *
     * @return the auth token.
     */
    public AuthToken getAuthToken() {
        return authToken;
    }

}
