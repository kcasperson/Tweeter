package edu.byu.cs.tweeter.client.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.LoginPresenter;
import edu.byu.cs.tweeter.client.presenter.SignoutPresenter;
import edu.byu.cs.tweeter.client.view.LoginActivity;
import edu.byu.cs.tweeter.client.view.asyncTasks.LoginTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.SignoutTask;
import edu.byu.cs.tweeter.client.view.util.ImageUtils;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.SignoutRequest;
import edu.byu.cs.tweeter.model.service.response.SignoutResponse;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */

public class MainActivity extends AppCompatActivity implements SignoutPresenter.View, SignoutTask.Observer {

    private static final String LOG_TAG = "MainActivity";
    public static final String CURRENT_USER_KEY = "CurrentUser";
    public static final String STALKED_USER_KEY = "Stalkee";
    public static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    private boolean isFABOpen = false;
    protected FloatingActionButton fab1;
    protected FloatingActionButton fab2;
    protected FloatingActionButton fab3;
    protected FloatingActionButton fab4;
    private boolean stalking_mode;
    private User stalkee;
    private User user;
    private AuthToken authToken;
    SignoutPresenter presenter = new SignoutPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        organizeView();

        setSignout();

        setUpMenu();

        setText();
    }

    protected void organizeView(){
        user = (User) getIntent().getSerializableExtra(CURRENT_USER_KEY);
        if(user == null) {
            throw new RuntimeException("User not passed to activity");
        }

        stalkee = (User) getIntent().getSerializableExtra(STALKED_USER_KEY);
        if(stalkee == null) {
            stalking_mode = false;
            System.out.println("No Stalkee passed to main activity");
//            throw new RuntimeException("Stalkee not passed to activity");
        }
        else stalking_mode = true;

        authToken = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);
        if(authToken == null) {
            throw new RuntimeException("Auth token not passed to activity");
        }

        //DONE: change user to stalkee on next line when stalking, user otherwise
        //TODO test this
        SectionsPagerAdapter sectionsPagerAdapter;
        if(stalking_mode){
            sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), stalkee, authToken);
        }
        else {
            System.out.println();
            System.out.println("NOT STALKING");
            System.out.println();
            sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), user, authToken);
        }
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    protected void setSignout(){

        Button signOut = findViewById(R.id.signOutButton);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignoutRequest request = new SignoutRequest(user, authToken);
                SignoutTask signoutTask = new SignoutTask(presenter, MainActivity.this);
                signoutTask.execute(request);
            }
        });
    }

    private void setUpMenu(){

        FloatingActionButton fab_menu = (FloatingActionButton) findViewById(R.id.fab_menu);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_profile);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_tweet);
        fab3 = (FloatingActionButton) findViewById(R.id.unfollow);
        fab4 = (FloatingActionButton) findViewById(R.id.follow);
        fab_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });


        fab1.setOnClickListener(new View.OnClickListener() { //see my profile
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.CURRENT_USER_KEY, user);
                intent.putExtra(MainActivity.STALKED_USER_KEY, user);
                intent.putExtra(MainActivity.AUTH_TOKEN_KEY, authToken);
                startActivity(intent);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make tweet
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You cannot unfollow yourself", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You cannot follow yourself", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    protected void showFABMenu(){
        isFABOpen=true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
        fab4.animate().translationY(-getResources().getDimension(R.dimen.standard_205));
    }

    protected void closeFABMenu(){
        isFABOpen=false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
        fab4.animate().translationY(0);
    }

    private void setText(){
        TextView userName = findViewById(R.id.userName);
        userName.setText(user.getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(user.getAlias());

        ImageView userImageView = findViewById(R.id.userImage);
        userImageView.setImageDrawable(ImageUtils.drawableFromByteArray(user.getImageBytes()));
    }

    protected User getUser() {
        return user;
    }

    protected AuthToken getAuthToken() {
        return authToken;
    }


    @Override
    public void signoutSuccessful(SignoutResponse signoutResponse) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void signoutUnsuccessful(SignoutResponse signoutResponse) {
        Toast.makeText(this, "Failed to signout. " + signoutResponse.getMessage(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void handleException(Exception ex) {
        Log.e(LOG_TAG, ex.getMessage(), ex);
        Toast.makeText(this, "Failed to login because of exception: " + ex.getMessage(), Toast.LENGTH_LONG).show();

    }
}