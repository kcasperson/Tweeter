package edu.byu.cs.tweeter.client.model.service;

import org.junit.Test;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import static org.junit.Assert.*;

public class StoryServiceProxyTest {

    private static final String URL_PATH = "/getstory";

    @Test
    public void getStory() {
        User user = new User("Test","User","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        Status status = new Status(user,"","");
        StoryRequest req = new StoryRequest(user,5,status);
        ServerFacade serverFacade = new ServerFacade();
        StoryResponse response = null;
        try {
            response = serverFacade.getStory(req, URL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }
}