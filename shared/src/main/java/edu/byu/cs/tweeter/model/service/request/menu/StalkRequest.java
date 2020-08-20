package edu.byu.cs.tweeter.model.service.request.menu;

import edu.byu.cs.tweeter.model.domain.User;

public class StalkRequest {
    private User user;
    public StalkRequest(User thisUser) {
        user = thisUser;
    }
}
