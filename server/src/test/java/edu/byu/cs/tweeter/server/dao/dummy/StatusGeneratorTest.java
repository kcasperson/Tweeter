package edu.byu.cs.tweeter.server.dao.dummy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusGeneratorTest {

    @Test
    void getInstance() {
    }

    @Test
    void getOneStatus() {
        String cupcakeIpsum = "Cupcake ipsum dolor. Sit amet liquorice jujubes. Tart gingerbread sesame snaps brownie.\n" +
                "\n" +
                "Jelly-o topping brownie dragée cupcake dessert. Topping cheesecake candy gummies macaroon dessert. Cake cake liquorice jujubes muffin toffee jujubes soufflé. Liquorice chocolate cake soufflé caramels cake muffin cookie candy.\n" +
                "\n" +
                "Sesame snaps gingerbread tootsie roll gummies gummies soufflé apple pie. Muffin gummi bears sweet dragée tootsie roll sesame snaps. Muffin jelly muffin cheesecake fruitcake cotton candy.\n" +
                "\n" +
                "Toffee lollipop muffin candy sesame snaps bonbon. Cupcake cheesecake soufflé tootsie roll candy canes caramels jujubes. Sesame snaps pastry sweet roll cookie macaroon marzipan cookie.\n" +
                "\n" +
                "Powder marzipan sweet roll fruitcake. Chupa chups halvah cake icing. Gummi bears pastry brownie bear claw pie lemon drops marshmallow sweet roll muffin. Wafer ice cream liquorice icing oat cake.";

        String[] cupcakeSentences = cupcakeIpsum.split("\\.");

        System.out.println(cupcakeSentences);

    }

    @Test
    void generateStories() {
    }
}