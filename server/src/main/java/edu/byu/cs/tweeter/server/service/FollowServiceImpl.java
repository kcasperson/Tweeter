package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FollowService;
import edu.byu.cs.tweeter.model.service.request.menu.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.menu.FollowResponse;
import edu.byu.cs.tweeter.server.dao.dummy.Initializer;


public class FollowServiceImpl implements FollowService {
    @Override
    public FollowResponse changeFollow(FollowRequest request) {
        return getInitializer().changeFollow(request);
    }

    private Initializer getInitializer(){
        return Initializer.getInstance();
    }
}
