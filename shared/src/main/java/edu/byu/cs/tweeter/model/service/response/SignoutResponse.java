package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.User;

public class SignoutResponse extends Response {
    private User user;

    public SignoutResponse(User user) {
        super(true, null);
        this.user = user;
    }

    public boolean isSuccess() {
        return super.isSuccess();
    }

    public User getUser() {
        return user;
    }
}
