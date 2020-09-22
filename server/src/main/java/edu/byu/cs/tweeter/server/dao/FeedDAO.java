package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.dao.dummy.FollowGenerator;
import edu.byu.cs.tweeter.server.dao.dummy.Initializer;
import edu.byu.cs.tweeter.server.dao.dummy.UserGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedDAO {

    /**
     * Gets the users from the database that the user specified in the request is following. Uses
     * information in the request object to limit the number of followees returned and to return the
     * next set of followees after any that were returned in a previous request. The current
     * implementation returns generated data and doesn't actually access a database.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the followees.
     */
    public FeedResponse getFeed(FeedRequest request) {
        // TODO: Generates dummy data. Replace with a real implementation.



//        String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
//        // Add the test user and make him follow everyone
//        User testUser = new User("Test", "User", MALE_IMAGE_URL);

        Map<User, List<Status>> feedByUser = Initializer.getInstance().getFeedByUser();
        List<Status> fullFeed = feedByUser.get(request.getUser());
//        List<Status> fullFeed = feedByUser.get(testUser);
        List<Status> responseFeed = new ArrayList<>(request.getLimit());

        System.out.println(fullFeed.toString());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            if (fullFeed != null) {
                int feedIndex = getFeedStartingIndex(request.getLastStatus(), fullFeed);

                for (int limitCounter = 0; feedIndex < fullFeed.size() && limitCounter < request.getLimit(); feedIndex++, limitCounter++) {
                    responseFeed.add(fullFeed.get(feedIndex));
                }

                hasMorePages = feedIndex < fullFeed.size();
            }
        }
        System.out.println("******* Server-side FeedDAO responseFeed *********");
        System.out.println(responseFeed.toString());
        System.out.println("*******Now calling FeedResponse*********");

        return new FeedResponse(responseFeed, hasMorePages);
    }

    /**
     * Determines the index for the first followee in the specified 'allFollowees' list that should
     * be returned in the current request. This will be the index of the next followee after the
     * specified 'lastFollowee'.
     *
     * @param lastStatus the last followee that was returned in the previous request or null if
     *                     there was no previous request.
     * @param fullFeed the generated list of followees from which we are returning paged results.
     * @return the index of the first followee to be returned.
     */
    private int getFeedStartingIndex(Status lastStatus, List<Status> fullFeed) {

        int feedIndex = 0;

        if(lastStatus != null) {
            // This is a paged request for something after the first page. Find the first item
            // we should return
            for (int i = 0; i < fullFeed.size(); i++) {
                if(lastStatus.equals(fullFeed.get(i))) {
                    // We found the index of the last item returned last time. Increment to get
                    // to the first one we should return
                    feedIndex = i + 1;
                }
            }
        }

        return feedIndex;
    }


    /**
     * Generates the followee data.
     */
    private Map<User, List<User>> initializeFollowees() {

        Map<User, List<User>> followeesByFollower = new HashMap<>();

        List<Follow> follows = getFollowGenerator().generateUsersAndFollows(100,
                0, 50, FollowGenerator.Sort.FOLLOWER_FOLLOWEE);

        // Populate a map of followees, keyed by follower so we can easily handle followee requests
        for(Follow follow : follows) {
            List<User> followees = followeesByFollower.get(follow.getFollower());

            if(followees == null) {
                followees = new ArrayList<>();
                followeesByFollower.put(follow.getFollower(), followees);
            }

            followees.add(follow.getFollowee());
        }

        return followeesByFollower;
    }

    /**
     * Returns an instance of FollowGenerator that can be used to generate Follow data. This is
     * written as a separate method to allow mocking of the generator.
     *
     * @return the generator.
     */
    FollowGenerator getFollowGenerator() {
        return FollowGenerator.getInstance();
    }
}
