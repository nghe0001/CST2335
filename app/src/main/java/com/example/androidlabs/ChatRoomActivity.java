package com.example.androidlabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<MsgLayout> listMessage = new ArrayList<>();
    Button SendBut;
    Button RecBut;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatroom);

        listView = (ListView)findViewById(R.id.ListView);
        editText = (EditText)findViewById(R.id.ChatBoxTxt);
        SendBut = (Button)findViewById(R.id.SendBut);
        RecBut = (Button)findViewById(R.id.RecBut);

        ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());

        SendBut.setOnClickListener(c -> {
            String message = editText.getText().toString();
            MsgLayout model = new MsgLayout(message, true);
            listMessage.add(model);
            editText.setText("");
            adt.notifyDataSetChanged();
        });

        RecBut.setOnClickListener(c -> {
            String message = editText.getText().toString();
            MsgLayout model = new MsgLayout(message, false);
            listMessage.add(model);
            editText.setText("");
            adt.notifyDataSetChanged();
        });



        listView.setAdapter(adt);
        Log.d("ChatRoomActivity","onCreate");

    }


}
