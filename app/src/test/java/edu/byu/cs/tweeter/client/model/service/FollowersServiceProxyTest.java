package edu.byu.cs.tweeter.client.model.service;

import org.junit.Test;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import static org.junit.Assert.*;

public class FollowersServiceProxyTest {

    static final String URL_PATH = "/getfollowers";

    @Test
    public void getFollowers() {
        User user = new User("Test","User","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        FollowersRequest req = new FollowersRequest(user, 3, new User("","",""));
        ServerFacade serverFacade = new ServerFacade();
        FollowersResponse response = null;
        try {
            response = serverFacade.getFollowers(req, URL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }
}