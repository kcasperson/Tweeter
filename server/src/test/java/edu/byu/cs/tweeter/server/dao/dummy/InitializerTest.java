package edu.byu.cs.tweeter.server.dao.dummy;



import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;

import static org.junit.jupiter.api.Assertions.*;

class InitializerTest {

    @Test
    void getInstance() {
        Initializer init = Initializer.getInstance();
        assertNotNull(init.getUsers());
        assertNotNull(init.getFollows());
    }

    @Test
    void getFollows() {
        Initializer init = Initializer.getInstance();
        List<Follow> follows = init.getFollows();
        assert(follows.size() > 2000);
    }

    @Test
    void getUsers() {
        Initializer init = Initializer.getInstance();
        List<User> users = init.getUsers();
        User user0 = users.get(0);
        assert(user0.getFirstName().equals("Lyman"));
    }

    @Test
    void getStoryByUser() {
        Initializer init = Initializer.getInstance();
        List<User> users = init.getUsers();
        assertEquals(users.size(), init.getStoryByUser().size());
    }

    @Test
    void getFeedByUser() {
        Initializer init = Initializer.getInstance();
        List<User> users = init.getUsers();
        assertEquals(users.size(), init.getFeedByUser().size());
    }
}