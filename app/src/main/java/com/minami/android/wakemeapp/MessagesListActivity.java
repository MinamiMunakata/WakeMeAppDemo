package com.minami.android.wakemeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.minami.android.wakemeapp.Model.Message;
import com.minami.android.wakemeapp.Model.User;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;

public class MessagesListActivity extends AppCompatActivity {
    private MessagesListAdapter<Message> adapter;
    private String senderId;
    private ImageLoader imageLoader;
    private MessagesList messagesList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_list);
        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.get().load(url).into(imageView);
//                Picasso.get(MessagesListActivity.this).load(url).into(imageView);
            }
        };
        adapter =new MessagesListAdapter<Message>("id", null);
        final User minami = new User("373", "minami");
        messagesList = findViewById(R.id.messagesList);
        MessageInput input = findViewById(R.id.input);
        input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                //validate and send message
                String input_text = input.toString();
                Message message = new Message("373", minami, input_text);
                adapter.addToStart(message, true);
                return true;
            }
        });
        messagesList.setAdapter(adapter);
        // TODO connect to database
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
    @Override
    protected void onStart() {
        super.onStart();
        // TODO read DB
//        adapter.addToStart(, true);
//        adapter = new MessagesListAdapter<>(senderId, imageLoader);
//        messagesList.setAdapter(adapter);
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
