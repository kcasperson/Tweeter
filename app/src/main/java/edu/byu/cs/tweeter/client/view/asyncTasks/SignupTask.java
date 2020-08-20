package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;
import android.util.Log;
import edu.byu.cs.tweeter.client.presenter.SignupPresenter;
import edu.byu.cs.tweeter.client.util.ByteArrayUtils;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.SignupResponse;

import java.io.IOException;

public class SignupTask extends AsyncTask<SignupRequest, Void, SignupResponse> {

    private final SignupPresenter presenter;
    private final Observer observer;
    private Exception exception;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void loginSuccessful(LoginResponse loginResponse);
        void loginUnsuccessful(LoginResponse loginResponse);
        void handleException(Exception ex);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter this task should use to login.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public SignupTask(SignupPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    /**
     * The method that is invoked on a background thread to log the user in. This method is
     * invoked indirectly by calling {@link #execute(SignupRequest...)}.
     *
     * @param signupRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected SignupResponse doInBackground(SignupRequest... signupRequests) {
        SignupResponse signupResponse = null;

        try {
            signupResponse = presenter.signup(signupRequests[0]);

            if(signupResponse.isSuccess()) {
                loadImage(signupResponse.getUser());
            }
        } catch (IOException | TweeterRemoteException ex) {
        exception = ex;
        }
        return signupResponse;
    }

    /**
     * Loads the profile image for the user.
     *
     * @param user the user whose profile image is to be loaded.
     */
    private void loadImage(User user) {
        try {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        } catch (IOException e) {
            Log.e(this.getClass().getName(), e.toString(), e);
        }
    }

    /**
     * Notifies the observer (on the thread of the invoker of the
     * {@link #execute(SignupRequest...)} method) when the task completes.
     *
     * @param signupResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(SignupResponse signupResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else if(signupResponse.isSuccess()) {
            observer.loginSuccessful(signupResponse);
        } else {
            observer.loginUnsuccessful(signupResponse);
        }
    }
}
