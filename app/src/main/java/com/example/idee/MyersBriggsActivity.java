package com.example.idee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MyersBriggsActivity extends AppCompatActivity {

    Switch IEswitch;
    Switch SNswitch;
    Switch TFswitch;
    Switch JPswitch;

    TextView description;

    String ie = "i";
    String sn = "s";
    String tf = "t";
    String jp = "j";

    private class JsoupParser extends AsyncTask<String, Void, Document> {
        @Override
        protected org.jsoup.nodes.Document doInBackground(String... strings ) {
            String website = "https://www.16personalities.com/";
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
            Elements paragraphs = doc.select("p");

            Log.d("message", String.valueOf(doc.select("p").size()));
            //Log.d("message", paragraphs.text());

            for (Element p : paragraphs) {
                Log.d("message", p.text());
            }
            description.setText(paragraphs.text());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myers_briggs);

        IEswitch = findViewById(R.id.IE);
        SNswitch = findViewById(R.id.SN);
        TFswitch = findViewById(R.id.TF);
        JPswitch = findViewById(R.id.JP);

        description = findViewById(R.id.mbText);

        IEswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ie = "e";
                } else {
                    // The toggle is disabled
                    ie = "i";
                }
            }
        } );

        SNswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sn = "n";
                } else {
                    // The toggle is disabled
                    sn = "s";
                }
            }
        } );

        TFswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tf = "f";
                } else {
                    // The toggle is disabled
                    tf = "t";
                }
            }
        } );

        JPswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    jp = "p";
                } else {
                    // The toggle is disabled
                    jp = "j";
                }
            }
        } );
    }

    public void pressedBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void pressedInfo(View view) {
        String type = ie + sn + tf + jp + "-personality";
        new JsoupParser().execute(type);
    }
}
