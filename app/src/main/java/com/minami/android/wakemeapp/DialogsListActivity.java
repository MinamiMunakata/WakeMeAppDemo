package com.minami.android.wakemeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.minami.android.wakemeapp.Controller.RealtimeDatabaseController;
import com.minami.android.wakemeapp.Model.Dialog;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

public class DialogsListActivity extends AppCompatActivity {
    private int dialogs;
    private DialogsList dialogsList;
    private static final String TAG = "DialogListActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs_list);
        Dialog dialog = new Dialog();
        dialogs = R.id.dialogsList;
        dialogsList = findViewById(dialogs);
        DialogsListAdapter dialogsListAdapter = new DialogsListAdapter<>(dialogs, new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                //If you using another library - write here your way to load image
                Picasso.get().load(url).into(imageView);
            }
        });

        dialogsList.setAdapter(dialogsListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    public void logout(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        launchMessagesListActivity();
                    }
                });
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
        // build a dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.find_friend_dialog_layout, null);
        builder.setView(dialogView);
        // init
        final EditText searchBox = view.findViewById(R.id.search_box);
        Button searchButton = view.findViewById(R.id.search_by_email_button);
        final TextView friendNameTextView = view.findViewById(R.id.friend_name_tv);
        Button addButton = view.findViewById(R.id.add_button);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(searchBox.getText().toString())){
                    return;
                }
                RealtimeDatabaseController.searchUserByEmail(
                        friendNameTextView,
                        searchBox.getText().toString());
            }
        });
        if (TextUtils.isEmpty(friendNameTextView.getText().toString())){
            return;
        }
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: ------------- add friend");
            }
        });


    }
}
