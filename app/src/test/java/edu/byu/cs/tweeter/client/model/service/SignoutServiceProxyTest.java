package edu.byu.cs.tweeter.client.model.service;

import org.junit.Test;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.SignoutRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.SignoutResponse;

import static org.junit.Assert.*;

public class SignoutServiceProxyTest {

    private static final String URL_PATH = "/signout";

    @Test
    public void signout() {
        User user = new User("Test","User","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        SignoutRequest req = new SignoutRequest(user, new AuthToken());
        ServerFacade serverFacade = new ServerFacade();
        SignoutResponse response = null;
        try {
            response = serverFacade.signout(req, URL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }
}