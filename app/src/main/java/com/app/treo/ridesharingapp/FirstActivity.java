package com.app.treo.ridesharingapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    ImageButton ib1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ib1 = (ImageButton) findViewById(R.id.dummy_button);
        SharedPreferences spref = getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
        final String a = spref.getString(BaseActivity.Name, null); // getting String
        final String b = spref.getString(BaseActivity.Phone, null);
        final String c = spref.getString(BaseActivity.IS_LOGIN, "false");


        ib1.setOnClickListener(v -> {


            Log.e("in FullScreen Activity ", "name : " + a + " phone : " + b + " isloggedin? : " + c);
            if (c.equals("true")) {
                Toast.makeText(FirstActivity.this, "Welcome Fellow User", Toast.LENGTH_SHORT).show();
                Log.e("in  Activity in if", "name : " + a + " phone : " + b + " isloggedin? : " + c);
                Intent i = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(FirstActivity.this, "Start Saving money with Ride App TODAY!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(FirstActivity.this, LoginBaseActivity.class);
                startActivity(i);
                finish();
            }

        });
    }
}


