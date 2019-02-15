package com.example.androidlabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    SharedPreferences share;
    EditText emailField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button nextButton = (Button)findViewById(R.id.loginButton);

        emailField = (EditText)findViewById(R.id.typeEmail);
        share = getSharedPreferences("FileName", Context.MODE_PRIVATE);
        String saveEmail = share.getString("ReserveName", "Default value");


        emailField.setHint(saveEmail);
        nextButton.setOnClickListener( b -> {


            Intent nextPage = new Intent(MainActivity.this, ProfileActivity.class);
            nextPage.putExtra("emailTyped", emailField.getText().toString());
            startActivityForResult(nextPage, 345);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = share.edit();
        String typeEmail  = emailField.getText().toString();
        editor.putString("ReserveName", typeEmail);
        editor.commit();
    }
}