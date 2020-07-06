package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FollowService;
import edu.byu.cs.tweeter.model.service.request.menu.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.menu.FollowResponse;

import java.io.IOException;

public class FollowServiceProxy implements FollowService {

    private static final String URL_PATH = "/follow";

    public FollowResponse changeFollow(FollowRequest request) throws IOException, TweeterRemoteException {
        getServerFacade().authorized();

        FollowResponse response = getServerFacade().changeFollow(request, URL_PATH);
        return response;
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
