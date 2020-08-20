package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;
import edu.byu.cs.tweeter.client.presenter.StoryPresenter;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import java.io.IOException;

public class GetStoryTask extends AsyncTask<StoryRequest, Void, StoryResponse> {

    private final StoryPresenter presenter;
    private final Observer observer;
    private Exception exception;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void statusesRetrieved(StoryResponse storyResponse);
        void handleException(Exception exception);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter from whom this task should retrieve followees.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public GetStoryTask(StoryPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }


    @Override
    protected StoryResponse doInBackground(StoryRequest... storyRequests) {
        StoryResponse response = null;

        try {
            response = presenter.getStory(storyRequests[0]);
        } catch (IOException | TweeterRemoteException ex) {
            exception = ex;
        }

        return response;
    }

    @Override
    protected void onPostExecute(StoryResponse response) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.statusesRetrieved(response);
        }
    }
}
