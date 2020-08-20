package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.util.ByteArrayUtils;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.SignoutService;
import edu.byu.cs.tweeter.model.service.SignupService;
import edu.byu.cs.tweeter.model.service.request.SignoutRequest;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.response.SignoutResponse;
import edu.byu.cs.tweeter.model.service.response.SignupResponse;

import java.io.IOException;

public class SignoutServiceProxy implements SignoutService {

    private static final String URL_PATH = "/signout";

    public SignoutResponse signout(SignoutRequest signoutRequest) throws IOException, TweeterRemoteException {
        ServerFacade ServerFacade = getServerFacade();
        SignoutResponse signoutResponse = ServerFacade.signout(signoutRequest, URL_PATH);

        return signoutResponse;
    }

    /**
     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
     * method to allow for proper mocking.
     *
     * @return the instance.
     */
    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
