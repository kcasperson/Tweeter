package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.StalkService;
import edu.byu.cs.tweeter.model.service.request.menu.StalkRequest;
import edu.byu.cs.tweeter.model.service.response.menu.StalkResponse;

import java.io.IOException;

public class StalkServiceProxy implements StalkService {

    private static final String URL_PATH = "/stalk";

    public StalkResponse stalk(StalkRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        StalkResponse stalkResponse = serverFacade.stalk(request, URL_PATH);



        return stalkResponse;
    }

    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
