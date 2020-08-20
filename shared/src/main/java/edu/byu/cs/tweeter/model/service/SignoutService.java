package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.SignoutRequest;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.response.SignoutResponse;
import edu.byu.cs.tweeter.model.service.response.SignupResponse;

import java.io.IOException;

public interface SignoutService {
    SignoutResponse signout(SignoutRequest request) throws IOException, TweeterRemoteException;

}
