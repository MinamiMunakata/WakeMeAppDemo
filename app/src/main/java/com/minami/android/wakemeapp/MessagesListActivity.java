package com.minami.android.wakemeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.minami.android.wakemeapp.Model.Message;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
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
    }

    @Override
    protected void onStart() {
        super.onStart();
//        adapter.addToStart(, true);
//        adapter = new MessagesListAdapter<>(senderId, imageLoader);
//        messagesList.setAdapter(adapter);
    }
}
