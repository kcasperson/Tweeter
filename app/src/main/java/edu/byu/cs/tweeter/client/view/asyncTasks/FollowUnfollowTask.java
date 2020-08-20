package edu.byu.cs.tweeter.client.view.asyncTasks;


import android.os.AsyncTask;
import edu.byu.cs.tweeter.client.presenter.FollowPresenter;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.menu.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.menu.FollowResponse;

import java.io.IOException;

public class FollowUnfollowTask extends AsyncTask<FollowRequest, Void, FollowResponse> {

    private final FollowPresenter presenter;
    private final Observer observer;
    private Exception exception;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void followChanged(FollowResponse followResponse);
        void handleException(Exception exception);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter from whom this task should retrieve followees.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public FollowUnfollowTask(FollowPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }


    @Override
    protected FollowResponse doInBackground(FollowRequest... followRequests) {
        FollowResponse response = null;

        try {
            response = presenter.changeFollow(followRequests[0]);
        } catch (IOException | TweeterRemoteException ex) {
            exception = ex;
        }

        return response;
    }

    @Override
    protected void onPostExecute(FollowResponse followResponse) {
        if (exception != null) {
            observer.handleException(exception);
        } else {
            observer.followChanged(followResponse);
        }
    }
}
