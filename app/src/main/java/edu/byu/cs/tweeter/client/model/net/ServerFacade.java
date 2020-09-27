package edu.byu.cs.tweeter.client.model.net;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.*;
import edu.byu.cs.tweeter.model.service.request.menu.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.menu.StalkRequest;
import edu.byu.cs.tweeter.model.service.response.*;
import edu.byu.cs.tweeter.model.service.response.menu.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.menu.StalkResponse;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 */
public class ServerFacade {

    private static final String SERVER_URL = "https://12fezvqjdl.execute-api.us-east-2.amazonaws.com/beta";

    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
    private AuthToken myAuth = null;

    /**
     * Performs a login and if successful, returns the logged in user and an auth token.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public LoginResponse login(LoginRequest request, String urlPath) throws IOException, TweeterRemoteException {
        LoginResponse response = clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);

        if(response.isSuccess()) {
            myAuth = response.getAuthToken();
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void authorized() throws RuntimeException {
//        if(myAuth == null){
//            throw new RuntimeException("Unauthorized access. Login failed.");
//        }
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the followees.
     */
    public FollowingResponse getFollowees(FollowingRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        FollowingResponse response = clientCommunicator.doPost(urlPath, request, null, FollowingResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public FollowersResponse getFollowers(FollowersRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        FollowersResponse response = clientCommunicator.doPost(urlPath, request, null, FollowersResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public FeedResponse getFeed(FeedRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        FeedResponse response = clientCommunicator.doPost(urlPath, request, null, FeedResponse.class);
        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public FollowResponse changeFollow(FollowRequest request,String urlPath)
            throws IOException, TweeterRemoteException {
        FollowResponse response = clientCommunicator.doPost(urlPath, request, null, FollowResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public SignupResponse signup(SignupRequest request,String urlPath)
            throws IOException, TweeterRemoteException {
        SignupResponse response = clientCommunicator.doPost(urlPath, request, null, SignupResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public SignoutResponse signout(SignoutRequest request,String urlPath)
            throws IOException, TweeterRemoteException {
        SignoutResponse response = clientCommunicator.doPost(urlPath, request, null, SignoutResponse.class);

        if(response.isSuccess()) {
            myAuth = null;
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public StalkResponse stalk(StalkRequest request, String urlPath) throws IOException, TweeterRemoteException {
        StalkResponse response = clientCommunicator.doPost(urlPath, request, null, StalkResponse.class);

        if(response.isSuccess()) {
            authorized();
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public StoryResponse getStory(StoryRequest request, String urlPath) throws IOException, TweeterRemoteException {
        StoryResponse response = clientCommunicator.doPost(urlPath, request, null, StoryResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }
}