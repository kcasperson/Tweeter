package edu.byu.cs.tweeter.server.dao.dummy;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.menu.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.menu.FollowResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Initializer {
    private List<Follow> follows;
    private List<User> users;
    private Map<User, List<User>> followeesByFollower = new HashMap<>();
    private Map<User, List<User>> followersByFollowee = new HashMap<>();
    private Map<User, List<Status>> storyByUser = new HashMap<>();
    private final Map<User, List<Status>> feedByUser = new HashMap<>();
    private static String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    // Add the test user and make him follow everyone
    private User testUser = new User("Test", "User", MALE_IMAGE_URL);

    private static Initializer initializer;

    public static Initializer getInstance(){
        if (initializer == null){
            initializer =  new Initializer();
            initializer.initialize();
        }// if user doesn't match up if called a second time, auth error should be here
        return initializer;
    }

    public List<Follow> getFollows() {
        return follows;
    }

    public List<User> getUsers() {
        return users;
    }

    public Map<User, List<User>> getFolloweesByFollower() {
        return followeesByFollower;
    }

    public Map<User, List<User>> getFollowersByFollowee() {
        return followersByFollowee;
    }

    public Map<User, List<Status>> getStoryByUser() {
        return storyByUser;
    }

    public Map<User, List<Status>> getFeedByUser() {
        return feedByUser;
    }

    private void initialize() {
        follows = generateUsersAndFollows(100,
                0, 50, FollowGenerator.Sort.FOLLOWER_FOLLOWEE);
        followeesByFollower = generateFolloweesByFollower();
        followersByFollowee = generateFollowersByFollowee();
        generateFeedsAndStories();
    }

    private List<Follow> generateUsersAndFollows(int userCount, int minFollowersPerUser,
                                                int maxFollowersPerUser, FollowGenerator.Sort sortOrder) {
        users = UserGenerator.getInstance().generateUsers(userCount);
        List<Follow> follows = FollowGenerator.getInstance().generateFollowsForUsers(users, minFollowersPerUser, maxFollowersPerUser, sortOrder);
        users.add(testUser);
        return follows;
    }

    private Map<User, List<User>> generateFolloweesByFollower(){
        Map<User, List<User>> fbfr = new HashMap<>();
        // Populate a map of followees, keyed by follower so we can easily handle followee requests
        for(Follow follow : follows) {
            List<User> followees = fbfr.computeIfAbsent(follow.getFollower(), k -> new ArrayList<>());
            followees.add(follow.getFollowee());
        }
        return fbfr;
    }

    private Map<User, List<User>> generateFollowersByFollowee() {
        Map<User, List<User>> followersByFolloweeMap = new HashMap<>();
        // Populate a map of followers, keyed by followee so we can easily handle follower requests
        for(Follow follow : follows) {
            List<User> followers = followersByFolloweeMap.computeIfAbsent(follow.getFollower(), k -> new ArrayList<>());

            followers.add(follow.getFollowee());
        }
        return followersByFolloweeMap;
    }

    private void generateFeedsAndStories() {
        storyByUser = StatusGenerator.getInstance().generateStories(users, 4);
        for (User curUser: users){
            System.out.println("Creating feed for " + curUser);
            List<Status> feed = new ArrayList<>();

            for(User followee : followeesByFollower.get(curUser)) {
                feed.addAll(storyByUser.get(followee));
            }

            feedByUser.put(curUser, feed);
        }
    }

    public FollowResponse changeFollow(FollowRequest request){
        List<User> myFollowees = followeesByFollower.get(request.getCurUser());
        boolean wantsToFollow = request.wantsToFollow();

        if (myFollowees == null) { //case for m3 lambda test, remove for m4
            return new FollowResponse(false, wantsToFollow);
        }

        boolean currentlyFollows = myFollowees.contains(request.getStalkee());
        //if the user is currently following the stalkee
        if(wantsToFollow) {
            if(currentlyFollows) {
                return new FollowResponse(true, true);
            }
            else {
                myFollowees.add(request.getStalkee());
                followeesByFollower.replace(request.getCurUser(),myFollowees);
                return new FollowResponse(false, true);
            }
        }
        else {
            if(currentlyFollows) {
                myFollowees.remove(request.getStalkee());
                followeesByFollower.replace(request.getCurUser(),myFollowees);
                return new FollowResponse(true, false);
            }
            else {
                return new FollowResponse(false, false);
            }

        }
    }
}
