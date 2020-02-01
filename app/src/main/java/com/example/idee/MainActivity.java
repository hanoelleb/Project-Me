package com.example.idee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectPersonalityTest(View view) {
        String buttonText = ((Button)view).getText().toString();

        if (buttonText.equals("Zodiac (Western)")) {
            Intent intent = new Intent(this, ZodWestActivity.class);
            startActivity(intent);
        } else if ( buttonText.equals("Zodiac (Eastern)")) {
            Intent intent = new Intent(this, ZodEastActivity.class);
            startActivity(intent);
        } else if ( buttonText.equals("Myers-Briggs")) {
            Intent intent = new Intent(this, MyersBriggsActivity.class);
            startActivity(intent);
        } else if ( buttonText.equals("Enneagram")) {
            Intent intent = new Intent(this, EnneagramActivity.class);
            startActivity(intent);
        } else if ( buttonText.equals("Four Temperaments")) {
            Intent intent = new Intent(this, FourTempActivity.class);
            startActivity(intent);
        } else if ( buttonText.equals("Big Five")) {
            Intent intent = new Intent(this, BigFiveActivity.class);
            startActivity(intent);
        }
    }
}
