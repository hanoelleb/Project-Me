package com.example.idee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyersBriggsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myers_briggs);
    }

    public void pressedBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
