package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;
import edu.byu.cs.tweeter.client.presenter.SignoutPresenter;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.SignoutRequest;
import edu.byu.cs.tweeter.model.service.response.SignoutResponse;

import java.io.IOException;

public class SignoutTask extends AsyncTask<SignoutRequest, Void, SignoutResponse> {

    private final SignoutPresenter presenter;
    private final SignoutTask.Observer observer;
    private Exception exception;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void signoutSuccessful(SignoutResponse signoutResponse);
        void signoutUnsuccessful(SignoutResponse signoutResponse);
        void handleException(Exception ex);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter this task should use to login.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public SignoutTask(SignoutPresenter presenter, SignoutTask.Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    /**
     * The method that is invoked on a background thread to log the user out. This method is
     * invoked indirectly by calling {@link #execute(SignoutRequest...)}.
     *
     * @param signoutRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected SignoutResponse doInBackground(SignoutRequest... signoutRequests) {
        SignoutResponse signoutResponse = null;

        try {
            signoutResponse = presenter.signout(signoutRequests[0]);
        } catch (IOException | TweeterRemoteException ex) {
            exception = ex;
        }
        return signoutResponse;
    }

    /**
     * Notifies the observer (on the thread of the invoker of the
     * {@link #execute(SignoutRequest...)} method) when the task completes.
     *
     * @param response the response that was received by the task.
     */
    @Override
    protected void onPostExecute(SignoutResponse response) {
        if(exception != null) {
            observer.handleException(exception);
        } else if(response.isSuccess()) {
            observer.signoutSuccessful(response);
        } else {
            observer.signoutUnsuccessful(response);
        }
    }
}

