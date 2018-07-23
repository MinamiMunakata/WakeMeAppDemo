package com.minami.android.wakemeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
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
import com.minami.android.wakemeapp.Controller.AuthenticationController;
import com.minami.android.wakemeapp.Model.User;

import java.util.Arrays;
import java.util.List;

import static com.minami.android.wakemeapp.Controller.AuthenticationController.mUserRef;


public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    List<AuthUI.IdpConfig> providers;
    private AppEventsLogger logger;
    private CallbackManager callbackManager;
    private LoginButton fbLoginButton;
    private static final String EMAIL = "email";
    private static final String PROFILE = "public_profile";
    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        //delete methods
//        AuthUI.getInstance()
//            .delete(this)
//            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        // Deletion succeeded
//                    } else {
//                        // Deletion failed
//                    }
//                    // ...
//                    Log.i(TAG, "onComplete: ---------------> onComplete");
//                }
//            });
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            // already signed in
            launchMessagesListActivity();
        } else {
            Log.i(TAG, "onCreate: nooooooooooooot sign iiiiiiiiiiiiiiiiin");
            // not signed in
            // Choose authentication providers
            providers = Arrays.asList(
                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.FacebookBuilder().build());
            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        }
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });

//        callbackManager = CallbackManager.Factory.create();

        logger = AppEventsLogger.newLogger(this);
//        fbLoginButton = findViewById(R.id.login_button);
//        fbLoginButton.setReadPermissions(Arrays.asList(EMAIL, PROFILE));
//        Log.i(TAG, "onCreate: " + "Heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey1");
//
//
//        // Callback registration
//        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.i(TAG, "onCreate: " + "Heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey2");
//
//
//                // App code
//                Log.i(TAG, "facebook:onSuccess:" + loginResult);
//                handleFacebookAccessToken(loginResult.getAccessToken());
//                launchDialogsListActivity();
//
//            }
//
//            @Override
//            public void onCancel() {
//                // App
//                Log.i(TAG, "onCreate: " + "Heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey3");
//
//                Log.i(TAG, "facebook:onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.i(TAG, "onCreate: " + "Heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey4");
//
//                // App code
//                Log.i(TAG, "facebook:onError", error);
//            }
//        });
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        Log.i(TAG, "onCreate: " + "Heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey5");
//
//        if (isLoggedIn) {
//            Log.i(TAG, "onCreate: " + "looooooooooooooooooooooooooooooooooooooooggedIn");
//        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                Log.i(TAG, "onActivityResult: oooooooooooooooooookaaaaaaaaaaaaaaaaaaayyyyyyyyyyyyyyyyyy");
                // Successfully signed in
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final String id = user.getUid();

                Log.i(TAG, "onActivityResult: ");
//                AuthenticationController.writeNewUser(user);
//                launchDialogsListActivity();

                // check Real Time database
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                final DatabaseReference mUserRef = rootRef.child("Users");
                mUserRef.child(id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()){
                            User newUser = new User(id, user.getDisplayName());
                            mUserRef.child(id).setValue(newUser);
                            Log.i(TAG, "onDataChange: --------> nooooooooooooooooooooooooooooooooooo");
                        } else {
                            Log.i(TAG, "onDataChange: --------> exist???");
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                launchMessagesListActivity();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Log.e(TAG, "Sign-in error: " + response.getError());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onCreate: " + "Heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey6");
        if (mAuth.getCurrentUser() != null) {
            // Successfully signed in
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final String id = user.getUid();

            Log.i(TAG, "onActivityResult: ");
//                AuthenticationController.writeNewUser(user);
//                launchDialogsListActivity();

            // check Real Time database
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            final DatabaseReference mUserRef = rootRef.child("Users");
            AuthenticationController.mUserRef.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()){
                        User newUser = new User(id, user.getDisplayName());
                        Log.i(TAG, "onDataChange: " + id);
                        Log.i(TAG, "onDataChange: " + user.getDisplayName());
                        Log.i(TAG, "onDataChange: --------> nooooooooooooooooooooooooooooooooooo");

                        mUserRef.child(id).setValue(newUser);
                    } else {
                        Log.i(TAG, "onDataChange: --------> exist???");
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            // already signed in
            launchMessagesListActivity();
        } else {
            // not signed in
        }

        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    private void launchDialogsListActivity() {
        Intent intent = new Intent(this, DialogsListActivity.class);
        startActivity(intent);
        finish();
    }

    private void launchMessagesListActivity() {
        Intent intent = new Intent(this, MessagesListActivity.class);
        startActivity(intent);
        finish();
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.i(TAG, "onCreate: " + "Heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey7");

        Log.i(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Log.i(TAG, "onCreate: " + "Heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey8");

    }


}
