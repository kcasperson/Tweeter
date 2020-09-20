package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.SignupService;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.response.SignupResponse;
import edu.byu.cs.tweeter.server.dao.dummy.Initializer;

import java.io.IOException;

public class SignupServiceImpl implements SignupService {
    @Override
    public SignupResponse signup(SignupRequest request) {
        System.out.println("Made it to server service signup");

//        //see if image was received as URL or byte[]
//        if(request.isURL()){
//            User user = new User(request.getFirstName(),
//                    request.getLastName(),
//                    request.getAlias(),
//                    request.getImageURL());
//            return new SignupResponse(user, new AuthToken());
//        }
//
//        User user = new User(request.getFirstName(),
//                request.getLastName(),
//                request.getAlias(),
//                request.getProfile());

        User user = new User(request.getFirstName(),
                request.getLastName(),
                request.getAlias(),
                //request.getImageURL());
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        Initializer.getInstance().addUser(user);
        return new SignupResponse(user, new AuthToken());

//        User user = new User("Test", "User",
//                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//        return new SignupResponse(user, new AuthToken());
    }
}
