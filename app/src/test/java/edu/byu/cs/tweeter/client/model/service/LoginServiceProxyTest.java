package edu.byu.cs.tweeter.client.model.service;

import org.junit.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.SignupResponse;

import static org.junit.Assert.*;

public class LoginServiceProxyTest {

    private static final String URL_PATH = "/login";

    @Test
    public void login() {
        LoginRequest req = new LoginRequest("@TestUser", "passw0rd");
        ServerFacade serverFacade = new ServerFacade();
        LoginResponse response = null;
        try {
            response = serverFacade.login(req, URL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }
}