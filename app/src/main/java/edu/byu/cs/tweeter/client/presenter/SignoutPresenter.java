package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.SignoutServiceProxy;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.SignoutService;
import edu.byu.cs.tweeter.model.service.request.SignoutRequest;
import edu.byu.cs.tweeter.model.service.response.SignoutResponse;

import java.io.IOException;

public class SignoutPresenter {


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
    public SignoutPresenter(View view) {
        this.view = view;
    }

    /**
     * Makes a login request.
     *
     * @param signoutRequest the request.
     */
    //todo doesnt this need to call the server facade?
    public SignoutResponse signout(SignoutRequest signoutRequest) throws IOException, TweeterRemoteException {
        SignoutService signoutService = new SignoutServiceProxy();
        return signoutService.signout(signoutRequest);
    }
}

