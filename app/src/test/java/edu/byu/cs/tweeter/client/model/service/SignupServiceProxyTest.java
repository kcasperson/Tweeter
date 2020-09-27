package edu.byu.cs.tweeter.client.model.service;

import org.junit.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.response.SignupResponse;

import static org.junit.Assert.*;

public class SignupServiceProxyTest {

    String URL_PATH = "/signup";

    @Test
    public void signup() {
        SignupRequest req = new SignupRequest("Araminta", "Leong", "minty","pass");
        ServerFacade serverFacade = new ServerFacade();
        SignupResponse signupResponse = null;
        try {
            signupResponse = serverFacade.signup(req, URL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(signupResponse);
    }
}