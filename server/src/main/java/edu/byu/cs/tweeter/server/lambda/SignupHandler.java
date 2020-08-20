package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.response.SignupResponse;
import edu.byu.cs.tweeter.server.service.SignupServiceImpl;

public class SignupHandler implements RequestHandler<SignupRequest, SignupResponse> {
        @Override
        public SignupResponse handleRequest(SignupRequest request, Context context) {
            System.out.println("Made it to server handler");
            SignupServiceImpl service = new SignupServiceImpl();
            return service.signup(request);
        }
    }

