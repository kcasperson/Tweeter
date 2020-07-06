package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;

import java.io.IOException;

public interface FollowersService {
    FollowersResponse getFollowers(FollowersRequest request)
            throws IOException, TweeterRemoteException;
}
