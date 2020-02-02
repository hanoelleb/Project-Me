package com.example.idee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FourTempActivity extends AppCompatActivity {

    TextView description;
    String tempStr;

    private class JsoupParser extends AsyncTask<String, Void, Document> {
        @Override
        protected org.jsoup.nodes.Document doInBackground(String... strings ) {
            String website = "https://psychologia.co/";
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
            Elements paragraphs = doc.select("p");

            Log.d("message", String.valueOf(doc.select("p").size()));

            paragraphs.remove(paragraphs.first());
            paragraphs.remove(paragraphs.last());

            String formatted = "";
            for (Element p : paragraphs) {
                formatted += p.text() + "\n";
            }
            description.setText(formatted);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_temp);

        description = findViewById(R.id.tempText);
    }

    public void pressedBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void pressedMel(View view) {
        tempStr = "melancholic-personality";
        new JsoupParser().execute(tempStr);
    }

    public void pressedPhlegm(View view){
        tempStr = "phlegmatic-personality";
        new JsoupParser().execute(tempStr);
    }

    public void pressedChol(View view) {
        tempStr = "choleric-personality";
        new JsoupParser().execute(tempStr);
    }

    public void pressedSang(View view) {
        tempStr = "sanguine-personality";
        new JsoupParser().execute(tempStr);
    }
}
