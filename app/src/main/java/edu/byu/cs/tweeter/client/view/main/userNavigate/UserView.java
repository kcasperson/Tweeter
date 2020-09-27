package edu.byu.cs.tweeter.client.view.main.userNavigate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.FollowPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.FollowUnfollowTask;
import edu.byu.cs.tweeter.client.view.main.MainActivity;
import edu.byu.cs.tweeter.client.view.util.ImageUtils;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.menu.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.menu.FollowResponse;

//TODO figure out if this class is necessary.
// Particularly look at how super.organizeView() could be implemented here
// vs moving stalkee functionality to the super class and getting rid of this one


@SuppressLint("Registered")
public class UserView extends MainActivity implements FollowPresenter.View, FollowUnfollowTask.Observer{
    public static final String STALKED_USER_KEY = "StalkedUser";
    private boolean isFABOpen = false;
    private User stalkee;
    private FollowPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.organizeView();
        super.setSignout();
        stalkee = (User) getIntent().getSerializableExtra(STALKED_USER_KEY);
        presenter = new FollowPresenter(this);

        setUpMenu();

        setText();
    }


    private void setUpMenu(){

        FloatingActionButton fab_menu = (FloatingActionButton) findViewById(R.id.fab_menu);
        super.fab1 = (FloatingActionButton) findViewById(R.id.fab_profile);
        super.fab2 = (FloatingActionButton) findViewById(R.id.fab_tweet);
        super.fab3 = (FloatingActionButton) findViewById(R.id.unfollow);
        super.fab4 = (FloatingActionButton) findViewById(R.id.follow);
        fab_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    UserView.super.showFABMenu();
                }else{
                    UserView.super.closeFABMenu();
                }
            }
        });

//        // We should use a Java 8 lambda function for the listener (and all other listeners), but
//        // they would be unfamiliar to many students who use this code.
        super.fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(UserView.this, MainActivity.class);
                intent.putExtra(MainActivity.CURRENT_USER_KEY, UserView.super.getUser());
                intent.putExtra(MainActivity.AUTH_TOKEN_KEY, UserView.super.getAuthToken());
                startActivity(intent);
            }
        });

        super.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make tweet
            }
        });

        super.fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FollowUnfollowTask task = new FollowUnfollowTask(presenter, UserView.this);
                FollowRequest request = new FollowRequest(stalkee, false);
                task.execute(request);
                Snackbar.make(view, stalkee.getName() + " unfollowed.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        super.fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FollowUnfollowTask task = new FollowUnfollowTask(presenter, UserView.this);
                FollowRequest request = new FollowRequest(stalkee, true);
                task.execute(request);

                Snackbar.make(view, stalkee.getName() + " followed.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setText(){
        TextView userName = findViewById(R.id.userName);
        userName.setText(stalkee.getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(stalkee.getAlias());

        ImageView userImageView = findViewById(R.id.userImage);
        userImageView.setImageDrawable(ImageUtils.drawableFromByteArray(stalkee.getImageBytes()));
    }

    @Override
    public void followChanged(FollowResponse followResponse) {

    }

    @Override
    public void handleException(Exception exception) {

    }
}
