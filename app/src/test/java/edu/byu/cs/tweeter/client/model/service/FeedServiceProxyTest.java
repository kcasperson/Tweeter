package edu.byu.cs.tweeter.client.model.service;

import org.junit.Test;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

import static org.junit.Assert.*;

public class FeedServiceProxyTest {

    String URL_PATH = "/getfeed";

    @Test
    public void getFeed() {
        User user = new User("Test","User","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        Status status = new Status(user,"","");
        FeedRequest req = new FeedRequest(user, 5, status);

        ServerFacade serverFacade = new ServerFacade();
        FeedResponse response = null;
        try {
            response = serverFacade.getFeed(req, URL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(response);
    }
}