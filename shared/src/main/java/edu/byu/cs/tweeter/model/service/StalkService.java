package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.menu.StalkRequest;
import edu.byu.cs.tweeter.model.service.response.menu.StalkResponse;

import java.io.IOException;

public interface StalkService {

    public StalkResponse stalk(StalkRequest request) throws IOException, TweeterRemoteException;
}
