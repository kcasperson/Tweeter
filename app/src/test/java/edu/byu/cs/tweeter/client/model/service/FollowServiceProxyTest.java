package edu.byu.cs.tweeter.client.model.service;

import org.junit.Test;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.menu.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.menu.FollowResponse;

import static org.junit.Assert.*;

public class FollowServiceProxyTest {

    private static final String URL_PATH = "/follow";

    @Test
    public void unFollow() {
        User user = new User("Test","User","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        FollowRequest req = new FollowRequest(user, new User("","",""), false);
        ServerFacade serverFacade = new ServerFacade();
        FollowResponse response = null;
        try {
            response = serverFacade.changeFollow(req, URL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }
}