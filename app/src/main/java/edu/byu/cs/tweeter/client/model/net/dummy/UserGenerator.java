package edu.byu.cs.tweeter.client.model.net.dummy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.byu.cs.tweeter.model.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A temporary class that generates and returns {@link User} objects. This class may be removed when
 * the server is created and the ServerFacade no longer needs to return dummy data.
 */
public class UserGenerator {

    private static final String MALE_NAMES_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/json/mnames.json";
    private static final String FEMALE_NAMES_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/json/fnames.json";
    private static final String SURNAMES_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/json/snames.json";

    private static final String [] maleNames;
    private static final String [] femaleNames;
    private static final String [] surnames;

    static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";

    private static UserGenerator instance;

    /**
     * A private constructor that ensures no instances of this class can be created.
     */
    private UserGenerator() {}

    /**
     * Returns the singleton instance of the class
     *
     * @return the instance.
     */
    public static UserGenerator getInstance() {
        if(instance == null) {
            instance = new UserGenerator();
        }

        return instance;
    }

    /*
     * Loads a lists of female first names, male first names, and surnames from the json files when
     * this class is loaded into memory.
     */
    static {
        try {
            maleNames = loadNamesFromJSon(MALE_NAMES_URL);
            femaleNames = loadNamesFromJSon(FEMALE_NAMES_URL);
            surnames = loadNamesFromJSon(SURNAMES_URL);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Loads and returns the names from the specified json file.
     *
     * @param urlString the url to the file containing the names.
     * @return the names.
     * @throws IOException if an IO error occurs.
     */
    private static String [] loadNamesFromJSon(String urlString) throws IOException {

        Names names;

        HttpURLConnection connection = null;

        try {
            URL url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Get response body input stream
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                names = (new Gson()).fromJson(br, Names.class);

            } else {
                throw new IOException("Unable to read from url. Response code: " + connection.getResponseCode());
            }

            connection.disconnect();
        } finally {
           if(connection != null) {
               connection.disconnect();
           }
        }

        return names == null ? null : names.getNames();
    }

    /**
     * Generates the specified number of users with names randomly selected from the json files.
     *
     * @param count the number of users to generate.
     * @return the generated users.
     */
    public List<User> generateUsers(int count) {
        Gson gson = new Gson();
        List<User> users = new ArrayList<>(count);
        String str = "[{\"alias\":\"@LymanMccants\",\"firstName\":\"Lyman\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Mccants\"},{\"alias\":\"@BlaineHousman\",\"firstName\":\"Blaine\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Housman\"},{\"alias\":\"@MagalyDurrell\",\"firstName\":\"Magaly\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Durrell\"},{\"alias\":\"@SharylDurrell\",\"firstName\":\"Sharyl\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Durrell\"},{\"alias\":\"@HunterSilvey\",\"firstName\":\"Hunter\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Silvey\"},{\"alias\":\"@DarronBeal\",\"firstName\":\"Darron\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Beal\"},{\"alias\":\"@PaulOgorman\",\"firstName\":\"Paul\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Ogorman\"},{\"alias\":\"@DeshawnCurington\",\"firstName\":\"Deshawn\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Curington\"},{\"alias\":\"@YvoneHeckel\",\"firstName\":\"Yvone\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Heckel\"},{\"alias\":\"@MauricioDennis\",\"firstName\":\"Mauricio\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Dennis\"},{\"alias\":\"@JenifferIvie\",\"firstName\":\"Jeniffer\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Ivie\"},{\"alias\":\"@LudivinaOrlowski\",\"firstName\":\"Ludivina\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Orlowski\"},{\"alias\":\"@FreddieMccawley\",\"firstName\":\"Freddie\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Mccawley\"},{\"alias\":\"@DeshawnWarnick\",\"firstName\":\"Deshawn\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Warnick\"},{\"alias\":\"@ParisTurck\",\"firstName\":\"Paris\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Turck\"},{\"alias\":\"@LarryAmsler\",\"firstName\":\"Larry\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Amsler\"},{\"alias\":\"@MohammedPaxton\",\"firstName\":\"Mohammed\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Paxton\"},{\"alias\":\"@GuillerminaGutierez\",\"firstName\":\"Guillermina\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Gutierez\"},{\"alias\":\"@SeegerMccane\",\"firstName\":\"Seeger\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Mccane\"},{\"alias\":\"@MadgeBabbitt\",\"firstName\":\"Madge\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Babbitt\"},{\"alias\":\"@VivanKissee\",\"firstName\":\"Vivan\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Kissee\"},{\"alias\":\"@SanoraMedina\",\"firstName\":\"Sanora\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Medina\"},{\"alias\":\"@MarshaLugo\",\"firstName\":\"Marsha\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Lugo\"},{\"alias\":\"@DylanVitagliano\",\"firstName\":\"Dylan\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Vitagliano\"},{\"alias\":\"@VenusIvie\",\"firstName\":\"Venus\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Ivie\"},{\"alias\":\"@WadeMurchison\",\"firstName\":\"Wade\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Murchison\"},{\"alias\":\"@PaulAnker\",\"firstName\":\"Paul\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Anker\"},{\"alias\":\"@DylanCubbage\",\"firstName\":\"Dylan\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Cubbage\"},{\"alias\":\"@ElvinNigro\",\"firstName\":\"Elvin\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Nigro\"},{\"alias\":\"@MarylouChiang\",\"firstName\":\"Marylou\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Chiang\"},{\"alias\":\"@MohammedFluellen\",\"firstName\":\"Mohammed\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Fluellen\"},{\"alias\":\"@KimberliHonea\",\"firstName\":\"Kimberli\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Honea\"},{\"alias\":\"@CaitlinKissee\",\"firstName\":\"Caitlin\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Kissee\"},{\"alias\":\"@PinkiePleasant\",\"firstName\":\"Pinkie\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Pleasant\"},{\"alias\":\"@HarrietteLazaro\",\"firstName\":\"Harriette\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Lazaro\"},{\"alias\":\"@ShaeMullan\",\"firstName\":\"Shae\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Mullan\"},{\"alias\":\"@GuillerminaDurrell\",\"firstName\":\"Guillermina\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Durrell\"},{\"alias\":\"@KimberliDennis\",\"firstName\":\"Kimberli\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Dennis\"},{\"alias\":\"@WadeTurck\",\"firstName\":\"Wade\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Turck\"},{\"alias\":\"@RooseveltEmory\",\"firstName\":\"Roosevelt\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Emory\"},{\"alias\":\"@AlphaKehr\",\"firstName\":\"Alpha\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Kehr\"},{\"alias\":\"@LarryZemlicka\",\"firstName\":\"Larry\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Zemlicka\"},{\"alias\":\"@StuartCrain\",\"firstName\":\"Stuart\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Crain\"},{\"alias\":\"@CelsaJeppesen\",\"firstName\":\"Celsa\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Jeppesen\"},{\"alias\":\"@GranvilleBleich\",\"firstName\":\"Granville\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Bleich\"},{\"alias\":\"@SidneyKelson\",\"firstName\":\"Sidney\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Kelson\"},{\"alias\":\"@CliffordMinott\",\"firstName\":\"Clifford\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Minott\"},{\"alias\":\"@RicoSchroyer\",\"firstName\":\"Rico\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Schroyer\"},{\"alias\":\"@MauricioLightfoot\",\"firstName\":\"Mauricio\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Lightfoot\"},{\"alias\":\"@DeshawnFluellen\",\"firstName\":\"Deshawn\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Fluellen\"},{\"alias\":\"@ThanhSacco\",\"firstName\":\"Thanh\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Sacco\"},{\"alias\":\"@CarmenMeiers\",\"firstName\":\"Carmen\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Meiers\"},{\"alias\":\"@LeiaLisenby\",\"firstName\":\"Leia\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Lisenby\"},{\"alias\":\"@PaulKissee\",\"firstName\":\"Paul\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Kissee\"},{\"alias\":\"@ElnaTaggert\",\"firstName\":\"Elna\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Taggert\"},{\"alias\":\"@VictorHeidelberg\",\"firstName\":\"Victor\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Heidelberg\"},{\"alias\":\"@BernardoMccane\",\"firstName\":\"Bernardo\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Mccane\"},{\"alias\":\"@LucienSchroyer\",\"firstName\":\"Lucien\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Schroyer\"},{\"alias\":\"@SantiagoIngalls\",\"firstName\":\"Santiago\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Ingalls\"},{\"alias\":\"@ElvinHoloman\",\"firstName\":\"Elvin\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Holoman\"},{\"alias\":\"@ElvinHeckel\",\"firstName\":\"Elvin\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Heckel\"},{\"alias\":\"@YuonneSilvey\",\"firstName\":\"Yuonne\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Silvey\"},{\"alias\":\"@LeannWarnick\",\"firstName\":\"Leann\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Warnick\"},{\"alias\":\"@AnetteKelson\",\"firstName\":\"Anette\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Kelson\"},{\"alias\":\"@GladysWoosley\",\"firstName\":\"Gladys\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Woosley\"},{\"alias\":\"@LeolaCritchfield\",\"firstName\":\"Leola\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Critchfield\"},{\"alias\":\"@SantoPink\",\"firstName\":\"Santo\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Pink\"},{\"alias\":\"@MaragaretVoris\",\"firstName\":\"Maragaret\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Voris\"},{\"alias\":\"@BartonEmory\",\"firstName\":\"Barton\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Emory\"},{\"alias\":\"@MarquittaMcnulty\",\"firstName\":\"Marquitta\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Mcnulty\"},{\"alias\":\"@TyreeSilverman\",\"firstName\":\"Tyree\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Silverman\"},{\"alias\":\"@HalWoosley\",\"firstName\":\"Hal\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Woosley\"},{\"alias\":\"@StuartLugo\",\"firstName\":\"Stuart\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Lugo\"},{\"alias\":\"@MarquittaLiao\",\"firstName\":\"Marquitta\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Liao\"},{\"alias\":\"@DaysiCessna\",\"firstName\":\"Daysi\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Cessna\"},{\"alias\":\"@RobbieDelcastillo\",\"firstName\":\"Robbie\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Delcastillo\"},{\"alias\":\"@WillieHudkins\",\"firstName\":\"Willie\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Hudkins\"},{\"alias\":\"@CarloLightfoot\",\"firstName\":\"Carlo\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Lightfoot\"},{\"alias\":\"@LeeHudkins\",\"firstName\":\"Lee\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Hudkins\"},{\"alias\":\"@WadeMarko\",\"firstName\":\"Wade\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Marko\"},{\"alias\":\"@LuettaBrace\",\"firstName\":\"Luetta\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Brace\"},{\"alias\":\"@JeniBlo\",\"firstName\":\"Jeni\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Blo\"},{\"alias\":\"@MarlonMcfarlain\",\"firstName\":\"Marlon\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Mcfarlain\"},{\"alias\":\"@BrandeGies\",\"firstName\":\"Brande\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Gies\"},{\"alias\":\"@BurtonSimien\",\"firstName\":\"Burton\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Simien\"},{\"alias\":\"@HollisCompton\",\"firstName\":\"Hollis\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Compton\"},{\"alias\":\"@ChanCaffee\",\"firstName\":\"Chan\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Caffee\"},{\"alias\":\"@WilbertKelson\",\"firstName\":\"Wilbert\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Kelson\"},{\"alias\":\"@JulianneHardt\",\"firstName\":\"Julianne\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Hardt\"},{\"alias\":\"@ChaunceyTurck\",\"firstName\":\"Chauncey\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Turck\"},{\"alias\":\"@LawrenceZemlicka\",\"firstName\":\"Lawrence\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Zemlicka\"},{\"alias\":\"@OdellHetherington\",\"firstName\":\"Odell\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Hetherington\"},{\"alias\":\"@DonaWarnick\",\"firstName\":\"Dona\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Warnick\"},{\"alias\":\"@DannaHardt\",\"firstName\":\"Danna\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Hardt\"},{\"alias\":\"@IonaMcnulty\",\"firstName\":\"Iona\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Mcnulty\"},{\"alias\":\"@HueyQuiroz\",\"firstName\":\"Huey\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Quiroz\"},{\"alias\":\"@MaynardBleich\",\"firstName\":\"Maynard\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Bleich\"},{\"alias\":\"@RobbieMeuser\",\"firstName\":\"Robbie\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"Meuser\"},{\"alias\":\"@KristyGies\",\"firstName\":\"Kristy\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Gies\"},{\"alias\":\"@AlphaRule\",\"firstName\":\"Alpha\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png\",\"lastName\":\"Rule\"},{\"alias\":\"@TestUser\",\"firstName\":\"Test\",\"imageUrl\":\"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png\",\"lastName\":\"User\"}]";
        Type type = new TypeToken<List<User>>(){}.getType();
        users = gson.fromJson(str, type);

//        Random random = new Random();
//
//        while(users.size() < count) {
//            // Randomly determine if the user will be male or female and generate a gender
//            // specific first name
//            String firstName;
//            String imageULR;
//            if(random.nextInt(2) == 0) {
//                firstName = maleNames[random.nextInt(maleNames.length)];
//                imageULR = MALE_IMAGE_URL;
//            } else {
//                firstName = femaleNames[random.nextInt(femaleNames.length)];
//                imageULR = FEMALE_IMAGE_URL;
//            }
//
//            String lastName = surnames[random.nextInt(surnames.length)];
//            User user = new User(firstName, lastName, imageULR);
//
//            if(!users.contains(user)) {
//                users.add(user);
//            }
//        }
//        System.out.println(users.toString());
        return users;
    }

    /**
     * A class used by Gson to map the json data to an instance of this class.
     */
    static class Names {

        @SuppressWarnings("unused")
        private String [] data;

        private String [] getNames() {
            return data;
        }
    }
}
