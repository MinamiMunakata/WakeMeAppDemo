package com.minami.android.wakemeapp.Controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minami.android.wakemeapp.Model.User;

/**
 * Created by Minami on 2018/07/21.
 */

public class AuthenticationController {
    private static final String USERS = "Users";
    private static final String MESSAGES = "Messages";
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference mUserRef = database.getReference(USERS);
    private static DatabaseReference mMessageRef;
//    = database.getReference(MESSAGES);
//    private static FirebaseUser mCurrentUser;

    public AuthenticationController() {
        database = FirebaseDatabase.getInstance();
        mUserRef = database.getReference(USERS);
        mMessageRef = database.getReference(MESSAGES);
//        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public static void initAuthenticationController() {
        database = FirebaseDatabase.getInstance();
        mUserRef = database.getReference(USERS);
        mMessageRef = database.getReference(MESSAGES);
    }

//    public static FirebaseUser getmCurrentUser() {
//        return mCurrentUser;
//    }

    public static boolean isNewUser(FirebaseUser mCurrentUser) {
        initAuthenticationController();
        if (mCurrentUser != null) {
            String id = mCurrentUser.getUid();
            DatabaseReference currentUserRef = mUserRef.child(id);
            if (currentUserRef == null) return true;
        }
        return false;
    }
    public static void writeNewUser(FirebaseUser mCurrentUser) {
        if (isNewUser(mCurrentUser)) {
            User mUser  = new User(mCurrentUser.getUid(), mCurrentUser.getDisplayName());
            mUserRef.child(mCurrentUser.getUid()).setValue(mUser);
        }
    }
}
