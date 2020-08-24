package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btnXuLy;
    TextView txtTextView;
    ImageView imgAndroid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnXuLy = findViewById(R.id.btn_XuLy);
        txtTextView = findViewById(R.id.txt_TextView);
        imgAndroid = findViewById(R.id.img_Android);
        btnXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CongViec().execute();
                new LoadImage().execute("https://get.wallhere.com/photo/soccer-stadium-Juventus-advertising-screenshot-computer-wallpaper-sport-venue-album-cover-13045.png");
            }
        });
    }
    private class CongViec extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtTextView.setText("Start"+"\n");
        }

        @Override
        protected String doInBackground(Void... voids) {

            for(int i=0; i<=5; i++){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress("Done! "+ i);
            }
            return "Done. \n";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtTextView.append(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtTextView.append(values[0]+"\n");
        }
    }
    private class LoadImage extends AsyncTask<String, Void , Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmapHinh = null;
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmapHinh = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmapHinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgAndroid.setImageBitmap(bitmap);
        }
    }
}