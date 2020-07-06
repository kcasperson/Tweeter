package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.Status;

import java.util.List;
import java.util.Objects;

public class StoryResponse extends PagedResponse{

    private List<Status> story;

    public StoryResponse(List<Status> story, boolean hasMorePages) {
        super(true, hasMorePages);
        this.story = story;
    }

    StoryResponse(boolean success, String message, boolean hasMorePages) {
        super(success, message, hasMorePages);
    }

    public List<Status> getStory() {
        return story;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoryResponse)) return false;
        StoryResponse that = (StoryResponse) o;
        return Objects.equals(story, that.story);
    }

    @Override
    public int hashCode() {
        return Objects.hash(story);
    }
}
