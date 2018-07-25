package com.minami.android.wakemeapp.Controller;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minami.android.wakemeapp.Model.User;

/**
 * Created by Minami on 2018/07/21.
 */

public class RealtimeDatabaseController {
    private static final String TAG = "RealtimeDBController";
    private static String id;
    private static final String USERS = "Users";
    private static final String MESSAGES = "Messages";
    public static final DatabaseReference USER_REF = FirebaseDatabase.getInstance().getReference(USERS);
    private static final DatabaseReference MESSAGE_REF = FirebaseDatabase.getInstance().getReference(MESSAGES);

    public static void addUserToRealtimeDB(final FirebaseUser USER, final String ID) {
        RealtimeDatabaseController.USER_REF.child(ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    RealtimeDatabaseController.writeNewUser(ID, USER.getDisplayName(), USER.getEmail());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: ", new Throwable(databaseError.getMessage()));
            }
        });
    }

    public static void writeNewUser(String id, String name, String email) {
        User newUser = new User(id, name, email);
        USER_REF.child(id).setValue(newUser);
    }

    public static void searchUserByEmail(final TextView FRIEND_NAME_TV, final String EMAIL){
        USER_REF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    if (EMAIL.equals(userSnapshot.child("email").getValue().toString())){
                        FRIEND_NAME_TV.setText(userSnapshot.child("name").getValue().toString());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: ", databaseError.toException());
            }
        });
    }
}
