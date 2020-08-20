package edu.byu.cs.tweeter.model.service.response.menu;

import edu.byu.cs.tweeter.model.service.response.Response;

import java.util.Objects;

public class FollowResponse extends Response{
    private boolean prevFollow;
    private boolean nowFollow;

    public FollowResponse(boolean prev, boolean now) {
        super(true);
        prevFollow = prev;
        nowFollow = now;
    }

    public boolean isPrevFollow() {
        return prevFollow;
    }

    public boolean isNowFollow() {
        return nowFollow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowResponse)) return false;
        FollowResponse that = (FollowResponse) o;
        return isPrevFollow() == that.isPrevFollow() &&
                isNowFollow() == that.isNowFollow();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPrevFollow(), isNowFollow());
    }

    @Override
    public String toString() {
        return "FollowResponse{" +
                "prevFollow=" + prevFollow +
                ", nowFollow=" + nowFollow +
                '}';
    }
}
