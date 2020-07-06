package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.Status;

import java.util.List;
import java.util.Objects;

public class FeedResponse extends PagedResponse{

    private List<Status> feed;

    public FeedResponse(List<Status> feed, boolean hasMorePages) {
        super(true, hasMorePages);
        System.out.println("******* Feed Response Constructor *********");
        System.out.println("Statuses in response: " + feed.toString());
        this.feed = feed;
    }

    FeedResponse(boolean success, String message, boolean hasMorePages) {
        super(success, message, hasMorePages);
    }

    public List<Status> getFeed() {
        return feed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedResponse)) return false;
        FeedResponse that = (FeedResponse) o;
        return getFeed().equals(that.getFeed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeed());
    }

    @Override
    public String toString() {
        return "FeedResponse{" +
                "statuses=" + feed +
                '}';
    }
}
