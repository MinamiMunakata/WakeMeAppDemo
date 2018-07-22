package com.minami.android.wakemeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

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
    protected void onStart() {
        super.onStart();
//        adapter.addToStart(, true);
//        adapter = new MessagesListAdapter<>(senderId, imageLoader);
//        messagesList.setAdapter(adapter);
    }
}
