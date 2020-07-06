package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.menu.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.menu.FollowResponse;

import java.io.IOException;

public interface FollowService {
    FollowResponse changeFollow(FollowRequest request) throws IOException, TweeterRemoteException;
}
