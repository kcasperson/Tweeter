package edu.byu.cs.tweeter.server.service;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.LoginService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.server.dao.dummy.Initializer;

public class LoginServiceImpl implements LoginService {

    @Override
    public LoginResponse login(LoginRequest request) {

        // TODO: Generates dummy data. Replace with a real implementation.

        List<User> allUsers = Initializer.getInstance().getUsers();
        String username = request.getUsername();

        for (int i = 0; i < allUsers.size() ; i++) {
            User user_i = allUsers.get(i);
            if (user_i.getAlias().equals(username)) {
                return new LoginResponse(user_i, new AuthToken());
            }
        }

        throw new RuntimeException("User not found on server-side, cannot login");
    }
}
