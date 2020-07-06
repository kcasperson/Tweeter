package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.SignupServiceProxy;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.SignupService;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.response.SignupResponse;

import java.io.IOException;

public class SignupPresenter {


    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public SignupPresenter(View view) {
        this.view = view;
    }

    /**
     * Makes a login request.
     *
     * @param signupRequest the request.
     */
    //todo doesnt this need to call the server facade?
    public SignupResponse signup(SignupRequest signupRequest) throws IOException, TweeterRemoteException {
        SignupService signupService = new SignupServiceProxy();
        return signupService.signup(signupRequest);
    }
}
