package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;
import edu.byu.cs.tweeter.client.presenter.StalkPresenter;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.menu.StalkRequest;
import edu.byu.cs.tweeter.model.service.response.menu.StalkResponse;

import java.io.IOException;

public class StalkTask extends AsyncTask<StalkRequest, Void, StalkResponse> {

    private final StalkPresenter presenter;
    private Observer observer;
    private Exception exception;

    public StalkTask(StalkPresenter stalkPresenter) {
        presenter = stalkPresenter;
    }

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void navigationSuccessful(StalkResponse stalkResponse);
        void navigationUnsuccessful(StalkResponse stalkResponse);
        void handleException(Exception ex);
    }

    public StalkTask(StalkPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected StalkResponse doInBackground(StalkRequest... stalkRequests) {
        StalkResponse stalkResponse = null;

        try {
            stalkResponse = presenter.stalk(stalkRequests[0]);

            if(stalkResponse.isSuccess()) {
                //
            }
        } catch (IOException | TweeterRemoteException ex) {
            exception = ex;
        }

        return stalkResponse;
    }



    /**
     * Notifies the observer (on the thread of the invoker of the
     * {@link #execute(StalkRequest...)} method) when the task completes.
     *
     * @param stalkResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(StalkResponse stalkResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else if(stalkResponse.isSuccess()) {
            observer.navigationSuccessful(stalkResponse);
        } else {
            observer.navigationUnsuccessful(stalkResponse);
        }
    }
}
