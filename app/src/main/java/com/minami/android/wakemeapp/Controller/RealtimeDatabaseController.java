package com.minami.android.wakemeapp.Controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minami.android.wakemeapp.Model.User;

/**
 * Created by Minami on 2018/07/21.
 */

public class RealtimeDatabaseController {
    private static String id;
    private static final String USERS = "Users";
    private static final String MESSAGES = "Messages";
    public static final DatabaseReference USER_REF = FirebaseDatabase.getInstance().getReference(USERS);
    private static final DatabaseReference MESSAGE_REF = FirebaseDatabase.getInstance().getReference(MESSAGES);

    public static void writeNewUser(String id, String name) {
        User newUser = new User(id, name);
        USER_REF.child(id).setValue(newUser);
    }
}
