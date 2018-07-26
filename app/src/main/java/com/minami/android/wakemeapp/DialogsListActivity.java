package com.minami.android.wakemeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.minami.android.wakemeapp.Controller.RealtimeDatabaseController;
import com.minami.android.wakemeapp.Model.ChatRoom;
import com.minami.android.wakemeapp.Model.Dialog;
import com.minami.android.wakemeapp.Model.User;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.minami.android.wakemeapp.Controller.RealtimeDatabaseController.CHAT_ROOM_REF;
import static com.minami.android.wakemeapp.Controller.RealtimeDatabaseController.USER_REF;

public class DialogsListActivity extends AppCompatActivity {
    private DialogsList dialogsListView;
    private static final String TAG = "DialogListActivity";
    private TextView friendNameTextView;
    private List<User> member;
    private final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private HashSet<User> set;
    private DialogsListAdapter dialogsListAdapter;
    private EditText searchBox;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs_list);
        member = new ArrayList<>();
        set = new HashSet<>();
        dialogsListView = findViewById(R.id.dialogsList);
        dialogsListAdapter = new DialogsListAdapter(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.get().load(url).into(imageView);
            }
        });
        dialogsListView.setAdapter(dialogsListAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // logout
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        launchLoginActivity();
                    }
                });
        return true;
    }


    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void launchMessagesListActivity() {
        Intent intent = new Intent(this, MessagesListActivity.class);
        startActivity(intent);
        finish();
    }

    public void showFindFriendDialog(View view) {
        Log.i(TAG, "showFindFriendDialog: --------------->  press???");
        // build a dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.find_friend_dialog_layout, null);
        builder.setView(dialogView);
        // init
        searchBox = dialogView.findViewById(R.id.search_box);
        ImageButton searchButton = dialogView.findViewById(R.id.search_by_email_button);
        friendNameTextView = dialogView.findViewById(R.id.friend_name_tv);
        alertDialog = builder.create();
        alertDialog.show();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(searchBox.getText().toString())){
                    toast("Please enter an email address");
                    return;
                }
                searchUserByEmail(searchBox.getText().toString());
            }
        });
    }

    public void searchUserByEmail(final String EMAIL){
        Log.i(TAG, "searchUserByEmail: ----------------");
        USER_REF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User mUser = null;
                User friend = null;
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    if (EMAIL.equals(userSnapshot.child("email").getValue().toString())){
                        friendNameTextView.setText(userSnapshot.child("name").getValue().toString());
                        friend = userSnapshot.getValue(User.class);
                        Log.i(TAG, "onDataChange: ------ " + friend + "FEIIIIIIIEEEN");
                    }
                    if (userSnapshot.child("id").getValue().toString().equals(currentUser.getUid())){
                        Log.i(TAG, "onDataChange: equal----- equal----- equal----- equal-----");
                        mUser = userSnapshot.getValue(User.class);
                    }
                }
                Log.i(TAG, "onDataChange: HeEEEEEY");
                Log.i(TAG, "onDataChange: " + mUser + ": " + friend);
                if (mUser != null && friend != null) {
                    Log.i(TAG, "onDataChange: nuuuuuuuuuuuuuuuulllll???");
                    if (mUser.getFriendsList().size() > 0 &&
                            mUser.getFriendsList().contains(friend)) {
                        toast(friendNameTextView.getText().toString() +
                                "is already your friend");
                        return;

                    } else {
                        Log.i(TAG, "onDataChange: adddddddddddddddddd????????");
                        mUser.addFriendToList(friend);
                        addMember(mUser);
                    }
                    if (friend.getFriendsList().size() > 0 ||
                            !friend.getFriendsList().contains(mUser)) {
                        Log.i(TAG, "onDataChange: -------contain????????");
                        friend.addFriendToList(mUser);
                        addMember(friend);
                    }
                }
                if (TextUtils.isEmpty(friendNameTextView.getText().toString())){
                    toast("The email address is not found.");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: ", databaseError.toException());
            }
        });
    }

    public void addMember(User user) {
        Log.i(TAG, "addMember: -------------------------");
        if (!set.contains(user)){
            member.add(user);
            set.add(user);
            Log.i(TAG, "addMember: added!!!!!!!!!!!");
        } else {
            member.clear();
            set.clear();
            toast("Please select other one else");
        }
        Log.i(TAG, "addMember: " + member.toString());
    }

    private void toast(String msg) {
        Toast.makeText(
                DialogsListActivity.this,
                msg,
                Toast.LENGTH_SHORT).show();
    }

    public void createDialog(View view) {
        if (TextUtils.isEmpty(friendNameTextView.getText().toString())){
            toast("Search your friends");
            member.clear();
            return;
        }
        if (member.size() < 2) {
            toast("Search your friends");
            member.clear();
            Log.i(TAG, "createDialog: member: " + member.toString());
            return;
        }
        Log.i(TAG, "createDialog: ------------------");
        searchBox.setText("");
        friendNameTextView.setText("");
        Log.i(TAG, "createDialog: " + member.toString());
        for (User user: member) {
            Log.i(TAG, "createDialog: " + user.getId());
            Log.i(TAG, "createDialog: " + user);
            Log.i(TAG, "createDialog: " + user.getFriendsList().toString());
            Log.i(TAG, "createDialog: --------------------------------------");
        }

//        for (User user: member){
//            USER_REF.child(user.getId()).setValue(user);
//        }
//        String id = CHAT_ROOM_REF.push().getKey();
//        ChatRoom mChatRoom = new ChatRoom(id, member);
//        CHAT_ROOM_REF.child(id).setValue(mChatRoom);
//        Dialog dialog = new Dialog(id, member);
//        dialogsListAdapter.addItem(dialog);
//        dialogsListAdapter.notifyDataSetChanged();
        alertDialog.dismiss();

    }
}
