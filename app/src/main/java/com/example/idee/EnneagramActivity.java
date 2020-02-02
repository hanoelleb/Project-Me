package com.example.idee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class EnneagramActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner type;

    private TextView description;

    private ImageView graph;

    private Bitmap graphImg;

    private class JsoupParser extends AsyncTask<String, Void, Document> {
        @Override
        protected org.jsoup.nodes.Document doInBackground(String... strings ) {
            String website = "https://www.enneagraminstitute.com/";
            Log.d("message", website + strings[0]);
            org.jsoup.nodes.Document doc = null;
            try {
                doc = Jsoup.connect(website + strings[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Element picture = doc.select("img").get(1);
            String url = picture.absUrl("src");
            graphImg = getImageBitmap(url);

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
            graph.setImageBitmap(graphImg);
        }

        private Bitmap getImageBitmap(String url) {
            Bitmap bm = null;
            try {

                // See what we are getting
                Log.i("message", "" + url);

                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();

                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);

                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e("message", "Error getting bitmap", e);
            }
            return bm;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enneagram);

        type = findViewById(R.id.spinnerEnn);
        type.setOnItemSelectedListener(this);

        graph = findViewById(R.id.graph);

        description = findViewById(R.id.ennText);
    }

    public void pressedBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sign = "type-1";
        switch (position) {
            case 0:
                sign = "type-1";
                break;
            case 1:
                sign = "type-2";
                break;
            case 2:
                sign = "type-3";
                break;
            case 3:
                sign = "type-4";
                break;
            case 4:
                sign = "type-5";
                break;
            case 5:
                sign = "type-6";
                break;
            case 6:
                sign = "type-7";
                break;
            case 7:
                sign = "type-8";
                break;
            case 8:
                sign = "type-9";
                break;
        }

        //setSignText(sign);
        new JsoupParser().execute(sign);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
