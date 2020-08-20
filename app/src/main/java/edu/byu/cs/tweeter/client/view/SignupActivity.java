package edu.byu.cs.tweeter.client.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.SignupPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.SignupTask;
import edu.byu.cs.tweeter.client.view.main.MainActivity;
import edu.byu.cs.tweeter.model.service.request.SignupRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

import java.io.*;

public class SignupActivity extends AppCompatActivity implements SignupPresenter.View, SignupTask.Observer {


    private static final String LOG_TAG = "SignupActivity";

    private SignupPresenter signupPresenter;
    private Toast signupToast;
    private Toast confettiToast;

    private ImageView imageView = null;
    private byte[] profileImage = null;
    private EditText first_name;
    private EditText last_name;
    private EditText alias;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupPresenter = new SignupPresenter(this);
        imageView = findViewById(R.id.profile_swan);

        Button chooseImg = findViewById(R.id.chooseProfileImg);
        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                selectImage(SignupActivity.this);

            }
        });

        first_name = findViewById(R.id.addFirstName);
        last_name = findViewById(R.id.addLastName);
        alias = findViewById(R.id.userAlias);
        password = findViewById(R.id.usrPassword);
        assert profileImage != null;

        Button signupButton = findViewById(R.id.SignupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Makes a signup request.
             *
             * @param view the view object that was clicked.
             */
            @Override
            public void onClick(View view) {

                String strFirstName = first_name.getText().toString();
                String strLastName = last_name.getText().toString();
                String strAlias = alias.getText().toString();
                String strPass = password.getText().toString();

                if(allFilled(strFirstName, strLastName, strAlias, strPass)){
                    SignupRequest signupRequest = new SignupRequest(strFirstName,
                            strLastName,
                            strAlias,
                            strPass,
                            profileImage);
                    System.out.println(signupRequest.toString());
                    SignupTask signupTask = new SignupTask(signupPresenter, SignupActivity.this);
                    signupTask.execute(signupRequest);
                }
                else {
                    Toast.makeText(SignupActivity.this,"Required Fields Missing", Toast.LENGTH_SHORT).show();
                }
            }
        });


        TextView confetti = findViewById(R.id.confettiTextView);
        confetti.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                confettiToast = Toast.makeText(SignupActivity.this, "Feature To Be Implemented", Toast.LENGTH_LONG);
                confettiToast.show();
            }
        });

    }

    private boolean allFilled(String strFirstName, String strLastName, String strAlias, String strPass){
        String message = "Field required";
        boolean filled = true;

        if(TextUtils.isEmpty(strFirstName)) {
            first_name.setError(message);
            filled = false;
        }
        if (TextUtils.isEmpty(strLastName)) {
            last_name.setError(message);
            filled = false;
        }
        if (TextUtils.isEmpty(strAlias)) {
            alias.setError(message);
            filled = false;
        }
        if (TextUtils.isEmpty(strPass)) {
            password.setError(message);
            filled = false;
        }
        if (imageView == null){ // FIXME this is not working, need a new way to check if image has been selected
            first_name.setError("Please select a profile image");
        }

        return filled;
    }

    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    System.out.println("Case 0");

                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        setByteArray(selectedImage);
                        imageView.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    System.out.println("Case 1");

                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        FileInputStream fis = createFileInputStreamFromGalleryPath(selectedImage);

                        String cachePicFile = createCacheFile(fis);
                        Bitmap bitmapImg = BitmapFactory.decodeFile(cachePicFile);
                        setByteArray(bitmapImg);
                        imageView.setImageBitmap(bitmapImg);
//                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                        if (selectedImage != null) {
//                            Cursor cursor = getContentResolver().query(selectedImage,
//                                    filePathColumn, null, null, null);
//                            if (cursor != null) {
//                                cursor.moveToFirst();
//
//                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                                String picturePath = cursor.getString(columnIndex);
//
////                                FileInputStream fis = createFileInputStreamFromGalleryPath(picturePath, selectedImage);
////                                //create inputstream from picturePath file here
////
////
////                                String cachePicFile = createCacheFile(fis, "cachePicFile");
////                                imageView.setImageBitmap(BitmapFactory.decodeFile(cachePicFile));
//                                cursor.close();
//                            }
//                        }
                    }
                    break;
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        profileImage =  stream.toByteArray();
    }


    private FileInputStream createFileInputStreamFromGalleryPath(Uri pictureUri){
        ParcelFileDescriptor pfd = null;
//        Uri pictureUri = Uri.fromFile(new File(picturePath));
        try {
            pfd = this.getContentResolver().openFileDescriptor(pictureUri,
                    "r",null);
            System.out.println(pfd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert pfd != null;
        return new FileInputStream(pfd.getFileDescriptor());
    }

    private String createCacheFile(InputStream initialStream){
        byte[] buffer = new byte[0];
        try {
            buffer = new byte[initialStream.available()];
            initialStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (initialStream != null) {
                    initialStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File targetFile = new File(this.getCacheDir(), "cachePicFile");
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(targetFile);
            fos.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return targetFile.getAbsolutePath();
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

        signupToast.cancel();
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

