package edu.byu.cs.tweeter.client.model.service;

import org.junit.Test;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;

import static org.junit.Assert.*;

public class FollowingServiceProxyTest {

    static final String URL_PATH = "/following";

    @Test
    public void getFollowees() {
        User user = new User("Test","User","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        FollowingRequest req = new FollowingRequest(user, 3, new User("","",""));
        ServerFacade serverFacade = new ServerFacade();
        FollowingResponse response = null;
        try {
            response = serverFacade.getFollowees(req, URL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }
}