package edu.byu.cs.tweeter.model.service.request.menu;

import edu.byu.cs.tweeter.model.domain.User;

public class FollowRequest {
    private User curUser;
    private User stalkee;
    private boolean follow;

    private FollowRequest(){}

    public FollowRequest(User curUser, User stalkee, boolean follow) {
        this.curUser = curUser;
        this.stalkee = stalkee;
        this.follow = follow;
    }
    public FollowRequest(User stalkee, boolean follow) {
        User user = new User("Test", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        this.curUser = user;
        this.stalkee = stalkee;
        this.follow = follow;
    }

    public User getCurUser() {
        return curUser;
    }

    public void setCurUser(User curUser) {
        this.curUser = curUser;
    }

    public User getStalkee() {
        return stalkee;
    }

    public void setStalkee(User stalkee) {
        this.stalkee = stalkee;
    }

    public boolean wantsToFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }
}
