package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.FollowServiceProxy;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FollowService;
import edu.byu.cs.tweeter.model.service.request.menu.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.menu.FollowResponse;

import java.io.IOException;

public class FollowPresenter {
    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public FollowPresenter(View view) {
        this.view = view;
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    public FollowResponse changeFollow(FollowRequest request) throws IOException, TweeterRemoteException {
        FollowService followService = getFollowService();
        return followService.changeFollow(request);
    }

    /**
     * Returns an instance of {@link FollowService}. Allows mocking of the FollowingService class
     * for testing purposes. All usages of FollowingService should get their FollowingService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    FollowService getFollowService() {
        return new FollowServiceProxy();
    }
}
