package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.response.SignupResponse;

import java.io.IOException;

public interface SignupService {
    SignupResponse signup(SignupRequest request) throws IOException, TweeterRemoteException;
}
