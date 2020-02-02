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
import org.w3c.dom.Text;

import java.io.IOException;

public class ZodEastActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner zodiac;
    private TextView description;

    private class JsoupParser extends AsyncTask<String, Void, Document> {
        @Override
        protected org.jsoup.nodes.Document doInBackground(String... strings ) {
            String website = "https://chinesenewyear.net/zodiac/";
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
        setContentView(R.layout.activity_zod_east);

        zodiac = findViewById(R.id.EasternZodiac);
        zodiac.setOnItemSelectedListener(this);

        description = findViewById(R.id.descEast);
    }

    public void pressedBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sign = "rat";
        switch (position) {
            case 0:
                sign = "rat";
                break;
            case 1:
                sign = "ox";
                break;
            case 2:
                sign = "tiger";
                break;
            case 3:
                sign = "rabbit";
                break;
            case 4:
                sign = "dragon";
                break;
            case 5:
                sign = "snake";
                break;
            case 6:
                sign = "horse";
                break;
            case 7:
                sign = "goat";
                break;
            case 8:
                sign = "monkey";
                break;
            case 9:
                sign = "rooster";
                break;
            case 10:
                sign = "dog";
                break;
            case 11:
                sign = "pig";
                break;
        }

        //setSignText(sign);
        new ZodEastActivity.JsoupParser().execute(sign);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
