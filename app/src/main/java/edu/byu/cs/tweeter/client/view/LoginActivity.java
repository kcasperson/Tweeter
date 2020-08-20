package edu.byu.cs.tweeter.client.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.LoginPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.LoginTask;
import edu.byu.cs.tweeter.client.view.main.MainActivity;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

/**
 * Contains the minimum UI required to allow the user to login with a hard-coded user. Most or all
 * of this should be replaced when the back-end is implemented.
 */
public class LoginActivity extends AppCompatActivity implements LoginPresenter.View, LoginTask.Observer {

    private static final String LOG_TAG = "LoginActivity";

    private LoginPresenter presenter;
    private Toast loginInToast;
    private Toast signupToast;
    private Toast confettiToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        Button loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Makes a login request. The user is hard-coded, so it doesn't matter what data we put
             * in the LoginRequest object.
             *
             * @param view the view object that was clicked.
             */
            @Override
            public void onClick(View view) {
                loginInToast = Toast.makeText(LoginActivity.this, "Logging In", Toast.LENGTH_LONG);
                loginInToast.show();

                // It doesn't matter what values we put here. We will be logged in with a hard-coded dummy user.
                LoginRequest loginRequest = new LoginRequest("dummyUserName", "dummyPassword");
                LoginTask loginTask = new LoginTask(presenter, LoginActivity.this);
                loginTask.execute(loginRequest);
            }
        });


        TextView signupButton = findViewById(R.id.SignupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Makes a signup request.
             *
             * @param view the view object that was clicked.
             */
            @Override
            public void onClick(View view) {
                signupToast = Toast.makeText(LoginActivity.this, "Navigating to Sign Up", Toast.LENGTH_LONG);
                signupToast.show();

                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

//                // It doesn't matter what values we put here. We will be logged in with a hard-coded dummy user.
//                SignupRequest signupRequest = new SignupRequest("dummyUserName", "dummyPassword");
//                SignupTask signupTask = new SignupTask(signupPresenter, LoginActivity.this);
//                signupTask.execute(signupRequest);
            }
        });


        TextView confetti = findViewById(R.id.confettiTextView);
        confetti.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                confettiToast = Toast.makeText(LoginActivity.this, "Feature To Be Implemented", Toast.LENGTH_LONG);
                confettiToast.show();
            }
        });

    }






        /**
         * The callback method that gets invoked for a successful login. Displays the MainActivity.
         *
         * @param loginResponse the response from the login request.
         */
    @Override
    public void loginSuccessful(LoginResponse loginResponse) {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(MainActivity.CURRENT_USER_KEY, loginResponse.getUser());
        intent.putExtra(MainActivity.AUTH_TOKEN_KEY, loginResponse.getAuthToken());

        loginInToast.cancel();
        startActivity(intent);
    }

    /**
     * The callback method that gets invoked for an unsuccessful login. Displays a toast with a
     * message indicating why the login failed.
     *
     * @param loginResponse the response from the login request.
     */
    @Override
    public void loginUnsuccessful(LoginResponse loginResponse) {
        Toast.makeText(this, "Failed to login. " + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    /**
     * A callback indicating that an exception was thrown in an asynchronous method called on the
     * presenter.
     *
     * @param exception the exception.
     */
    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(this, "Failed to login because of exception: " + exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
