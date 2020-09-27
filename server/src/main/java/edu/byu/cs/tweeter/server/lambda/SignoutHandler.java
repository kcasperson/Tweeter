package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.SignoutRequest;
import edu.byu.cs.tweeter.model.service.response.SignoutResponse;
import edu.byu.cs.tweeter.server.service.SignoutServiceImpl;

public class SignoutHandler implements RequestHandler<SignoutRequest, SignoutResponse> {
    @Override
    public SignoutResponse handleRequest(SignoutRequest request, Context context) {
        SignoutServiceImpl service = new SignoutServiceImpl();
        return service.signout(request);
    }
}