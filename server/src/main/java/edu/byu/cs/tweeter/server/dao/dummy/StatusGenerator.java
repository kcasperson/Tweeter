package edu.byu.cs.tweeter.server.dao.dummy;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

import java.util.*;

public class StatusGenerator {

    private static StatusGenerator statusGenerator;

    private StatusGenerator(){}

    public static StatusGenerator getInstance(){
        if(statusGenerator == null){
            statusGenerator = new StatusGenerator();
        }
        return statusGenerator;
    }


    public String getOneStatus() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 140;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public Map<User, List<Status>> generateStories(List<User> users, int i) {
        Map<User, List<Status>> stories = new HashMap<>();

        for(User curUser: users){
            List<Status> story = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                Status status = new Status(curUser, getOneStatus(),"12-3-20");
                story.add(status);
            }
            stories.put(curUser,story);
        }

        return stories;
    }
}
