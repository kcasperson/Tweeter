package edu.byu.cs.tweeter.server.lambda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.SignoutRequest;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.request.menu.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.SignoutResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.model.service.response.menu.FollowResponse;

class HandlerTest {

    private Context context = new Context() {
        @Override
        public String getAwsRequestId() {
            return null;
        }

        @Override
        public String getLogGroupName() {
            return null;
        }

        @Override
        public String getLogStreamName() {
            return null;
        }

        @Override
        public String getFunctionName() {
            return null;
        }

        @Override
        public String getFunctionVersion() {
            return null;
        }

        @Override
        public String getInvokedFunctionArn() {
            return null;
        }

        @Override
        public CognitoIdentity getIdentity() {
            return null;
        }

        @Override
        public ClientContext getClientContext() {
            return null;
        }

        @Override
        public int getRemainingTimeInMillis() {
            return 0;
        }

        @Override
        public int getMemoryLimitInMB() {
            return 0;
        }

        @Override
        public LambdaLogger getLogger() {
            return null;
        }
    };
    private User user = new User("Test","User","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
    private Status status = new Status(user,"","");

    @Test
    void handleFollowRequest() {
        FollowHandler handler = new FollowHandler();
        FollowRequest req = new FollowRequest(user, new User("Alpha","Kehr",""), true);
        FollowResponse expected = new FollowResponse(true, true);

        FollowResponse res = handler.handleRequest(req, context);
        assertEquals(expected, res);
    }

    @Test
    void handleFeedRequest() {
        GetFeedHandler handler = new GetFeedHandler();
        FeedRequest req = new FeedRequest(user, 5, status);

        FeedResponse res = handler.handleRequest(req, context);
        assert(res.getFeed().size() > 2);
    }

    @Test
    void handleFollowersRequest() {
        GetFollowersHandler handler = new GetFollowersHandler();
        FollowersRequest req = new FollowersRequest(user, 3, new User("","",""));

        FollowersResponse res = handler.handleRequest(req, context);
        assert(res.getFollowers().size() > 1);
    }

    @Test
    void handleFollowingRequest() {
        GetFollowingHandler handler = new GetFollowingHandler();
        FollowingRequest req = new FollowingRequest(user, 3, new User("","",""));

        FollowingResponse res = handler.handleRequest(req, context);
        assert(res.getFollowees().size() > 1);
    }

    @Test
    void handleStoryRequest() {
        GetStoryHandler handler = new GetStoryHandler();
        StoryRequest req = new StoryRequest(user,5,status);

        StoryResponse res = handler.handleRequest(req, context);
        assert(res.getStory().size() >2);
    }

    @Test
    void handleLoginRequest() {
        LoginHandler handler = new LoginHandler();
        LoginRequest req = new LoginRequest("@TestUser", "passw0rd");

        LoginResponse res = handler.handleRequest(req, context);
        assert(res.getUser().getFirstName().equals("Test"));
    }

    @Test
    void handleSignupRequest() {
        SignupHandler handler = new SignupHandler();
        SignupRequest req = new SignupRequest("Araminta", "Lee", "minty","pass");

        LoginResponse res = handler.handleRequest(req, context);
        assertNotNull(res);
    }

    @Test
    void handleSignoutRequest() {
        SignoutHandler handler = new SignoutHandler();
        SignoutRequest req = new SignoutRequest(user, new AuthToken());

        SignoutResponse res = handler.handleRequest(req, context);
        assertNotNull(res);
    }
}