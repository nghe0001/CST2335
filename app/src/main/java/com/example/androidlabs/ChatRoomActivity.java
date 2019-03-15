package com.example.androidlabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<MsgLayout> listMessage = new ArrayList<>();
    Button SendBut;
    Button RecBut;
    TodoDatabaseHelper db;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatroom);

        listView = (ListView) findViewById(R.id.ListView);
        editText = (EditText) findViewById(R.id.ChatBoxTxt);
        SendBut = (Button) findViewById(R.id.SendBut);
        RecBut = (Button) findViewById(R.id.RecBut);
        db = new TodoDatabaseHelper(this);

        viewData();
//        ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());

        SendBut.setOnClickListener(c -> {
            String message = editText.getText().toString();
//            MsgLayout model = new MsgLayout(message, true);
            if (!message.equals("")) {

//          listMessage.add(model);
                db.insertData(message, true);

                editText.setText("");
                listMessage.clear();
                viewData();
//            adt.notifyDataSetChanged();
            }
        });

        RecBut.setOnClickListener(c -> {
            String message = editText.getText().toString();
//            MsgLayout model = new MsgLayout(message, false);
            if (!message.equals("")) {
                db.insertData(message, false);
                editText.setText("");
                listMessage.clear();
                viewData();


//            listMessage.add(model);

//            adt.notifyDataSetChanged();
            }
        });


//        listView.setAdapter(adt);
        Log.d("ChatRoomActivity", "onCreate");

    }

    private void viewData() {
        Cursor cursor = db.viewData();

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                MsgLayout model = new MsgLayout(cursor.getString(1), cursor.getInt(2) == 0 ? true : false);
                listMessage.add(model);
                ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());
                listView.setAdapter(adt);

            }
        }


    }
}