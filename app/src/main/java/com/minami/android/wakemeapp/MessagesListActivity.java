package com.minami.android.wakemeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minami.android.wakemeapp.Model.Author;
import com.minami.android.wakemeapp.Model.ChatRoom;
import com.minami.android.wakemeapp.Model.Message;
import com.minami.android.wakemeapp.Model.Text;
import com.minami.android.wakemeapp.Model.User;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.minami.android.wakemeapp.Controller.DBController.CHAT_ROOM_REF;
import static com.minami.android.wakemeapp.Controller.DBController.MESSAGE_REF;
import static com.minami.android.wakemeapp.Controller.DBController.USER_REF;

public class MessagesListActivity extends AppCompatActivity {
    public static final String TAG = "MessagesListActivity";
    private MessagesListAdapter<Message> adapter;
    private String senderId;
    private ImageLoader imageLoader;
    private MessagesList messagesListView;
    private FirebaseUser currentUser;
    private String chatRoomId;
    private List<Message> messageList;
    private List<User> member;
    private User mSender;
    private MessageInput input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_list);
        messageList = new ArrayList<>();
        member = new ArrayList<>();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent = getIntent();
        chatRoomId = intent.getStringExtra(ChatRoom.CHAT_ROOM_ID);
        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.get().load(url).into(imageView);
//                Picasso.get(MessagesListActivity.this).load(url).into(imageView);
            }
        };
        messagesListView = findViewById(R.id.messagesList);
        input = findViewById(R.id.input);
    }

    private void sendMessage(final User sender, MessageInput input) {
        Log.i(TAG, "sendMessage: 5555555555555");
        input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                //validate and send message
                String input_text = input.toString();
                String id = MESSAGE_REF.push().getKey();
                Message message = new Message(id, sender, input_text);
                Text text = message;
                adapter.addToStart(message, true);
                Log.i(TAG, "onSubmit: 6666666666666" + message);

                MESSAGE_REF.child(id).setValue(text);
                Log.i(TAG, "onSubmit: 6666666666666" + message);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        senderId = currentUser.getUid();
        MESSAGE_REF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageList.clear();
                for (DataSnapshot messageSnapshot: dataSnapshot.child(chatRoomId).getChildren()){
                    Message message = messageSnapshot.getValue(Message.class);
                    messageList.add(message);
                }
                adapter = new MessagesListAdapter<Message>(senderId, null);
                adapter.addToEnd(messageList, true);
                messagesListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        USER_REF.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User sender = dataSnapshot.child(senderId).getValue(User.class);
                Log.i(TAG, "onDataChange: " + sender);
                sendMessage(sender, input);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                        // logout
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
}
