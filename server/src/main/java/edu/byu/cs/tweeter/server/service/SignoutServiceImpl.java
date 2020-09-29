package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.SignoutService;
import edu.byu.cs.tweeter.model.service.request.SignoutRequest;
import edu.byu.cs.tweeter.model.service.response.SignoutResponse;

public class SignoutServiceImpl implements SignoutService {

        @Override
        public SignoutResponse signout(SignoutRequest request) {
//            request.getAuthToken().decommission();
            return new SignoutResponse(request.getUser());
        }

}
