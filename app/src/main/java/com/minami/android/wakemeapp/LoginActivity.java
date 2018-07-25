package com.minami.android.wakemeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minami.android.wakemeapp.Controller.RealtimeDatabaseController;
import com.minami.android.wakemeapp.Model.User;

import java.util.Arrays;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private List<AuthUI.IdpConfig> providers;
    private AppEventsLogger logger;
    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logger = AppEventsLogger.newLogger(this);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            RealtimeDatabaseController.addUserToRealtimeDB(
                    mAuth.getCurrentUser(),
                    mAuth.getCurrentUser().getUid());
            Log.i(TAG, "onCreate: -------------- 1");
            // already signed in
            launchMessagesListActivity();
        } else {
            Log.i(TAG, "onCreate: -------------- 2");

            // not signed in
            // Choose authentication providers
            providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.FacebookBuilder().build());
            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setIsSmartLockEnabled(false)
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onCreate: -------------- 3");

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                Log.i(TAG, "onCreate: -------------- 4");

                launchDialogsListActivity();
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Log.i(TAG, "onCreate: -------------- 5");

                Log.e(TAG, "Sign-in error: " + response.getError());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser user = mAuth.getCurrentUser();
        Log.i(TAG, "onCreate: -------------- 6");

        if (user != null) {
            final String id = user.getUid();
            // check Real Time database
            RealtimeDatabaseController.addUserToRealtimeDB(user, id);
            // already signed in
            launchDialogsListActivity();
        } else {
            Log.i(TAG, "onCreate: -------------- 7");

//            // not signed in
//            startActivityForResult(
//                    AuthUI.getInstance()
//                            .createSignInIntentBuilder()
//                            .setAvailableProviders(providers)
//                            .build(),
//                    RC_SIGN_IN);
        }
    }

    private void launchDialogsListActivity() {
        Intent intent = new Intent(LoginActivity.this, DialogsListActivity.class);
        startActivity(intent);
        finish();
    }

    private void launchMessagesListActivity() {
        Intent intent = new Intent(LoginActivity.this, MessagesListActivity.class);
        startActivity(intent);
        finish();
    }
}
