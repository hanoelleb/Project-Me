package com.example.idee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ZodWestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner zodiac;
    private TextView description;

    private class JsoupParser extends AsyncTask<String, Void, Document> {
        @Override
        protected org.jsoup.nodes.Document doInBackground(String... strings ) {
            String website = "https://www.astrology.com/astrology-101/zodiac-signs/";
            Log.d("message", website + strings[0]);
            org.jsoup.nodes.Document doc = null;
            try {
                doc = Jsoup.connect(website + strings[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            // execution of result here
            Element paragraphs = doc.select("p").first();
            description.setText(paragraphs.text());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zod_west);

        zodiac = findViewById(R.id.WesternZodiac);
        zodiac.setOnItemSelectedListener(this);

        description = findViewById(R.id.desc);
    }

    public void pressedBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sign = "aquarius";
        switch (position) {
            case 0:
                sign = "aquarius";
                break;
            case 1:
                sign = "pisces";
                break;
            case 2:
                sign = "aries";
                break;
            case 3:
                sign = "taurus";
                break;
            case 4:
                sign = "gemini";
                break;
            case 5:
                sign = "cancer";
                break;
            case 6:
                sign = "leo";
                break;
            case 7:
                sign = "virgo";
                break;
            case 8:
                sign = "libra";
                break;
            case 9:
                sign = "scorpio";
                break;
            case 10:
                sign = "sagittarius";
                break;
            case 11:
                sign = "capricorn";
                break;
        }

        //setSignText(sign);
        new JsoupParser().execute(sign);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setSignText( String sign ) {
        String website = "https://www.astrology.com/astrology-101/zodiac-signs/";
        Document doc = null;
        try {
            doc = Jsoup.connect(website + sign).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element paragraphs = doc.select("p").first();
        Log.d("message", String.valueOf(doc.select("p").size()));
        Log.d("message", paragraphs.text());
        description.setText(paragraphs.text());
    }
}

