package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.dao.FeedDAO;

public class FeedServiceImpl implements FeedService {
    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. Uses the {@link FeedDAO} to
     * get the followees.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    @Override
    public FeedResponse getFeed(FeedRequest request) {
        return getFeedDAO().getFeed(request);
    }

    /**
     * Returns an instance of {@link FeedDAO}. Allows mocking of the FollowingDAO class
     * for testing purposes. All usages of FollowingDAO should get their FollowingDAO
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    FeedDAO getFeedDAO() {
        return new FeedDAO();
    }
}

