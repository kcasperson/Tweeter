package edu.byu.cs.tweeter.client.model.net.dummy;

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

//        String generatedString = random.ints(leftLimit, rightLimit + 1)
//                .limit(targetStringLength)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();

        String cupcakeIpsum = "Cupcake ipsum dolor. Sit amet liquorice jujubes. Tart gingerbread sesame snaps brownie." +
                "Jelly-o topping brownie dragée cupcake dessert. Topping cheesecake candy gummies macaroon dessert. " +
                "Cake cake liquorice jujubes muffin toffee jujubes soufflé. Liquorice chocolate cake soufflé caramels cake muffin cookie candy." +
                "Sesame snaps gingerbread tootsie roll gummies gummies soufflé apple pie. Muffin gummi bears sweet dragée tootsie roll sesame snaps. " +
                "Muffin jelly muffin cheesecake fruitcake cotton candy. Toffee lollipop muffin candy sesame snaps bonbon. " +
                "Cupcake cheesecake soufflé tootsie roll candy canes caramels jujubes. Sesame snaps pastry sweet roll cookie macaroon marzipan cookie." +
                "Powder marzipan sweet roll fruitcake. Chupa chups halvah cake icing. Gummi bears pastry brownie bear claw pie lemon drops marshmallow sweet roll muffin. " +
                "Wafer ice cream liquorice icing oat cake.";

        String[] cupcakeSentences = cupcakeIpsum.split("\\.");
        int rand_i = random.nextInt(cupcakeSentences.length);
        return cupcakeSentences[rand_i];
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
