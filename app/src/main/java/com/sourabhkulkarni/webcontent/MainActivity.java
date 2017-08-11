package com.sourabhkulkarni.webcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import java.net.HttpURLConnection;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while ((data != -1)) {

                    char curreent = (char) data;
                    result += curreent;
                    data = reader.read();


                }
                return result;
            }
            catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
            //Log.i("Contents", result);
        }


        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            String result = null;
            DownloadTask dlt = new DownloadTask();
            try {
                result = dlt.execute("http://vcet.edu.in/").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Log.i("result", result);


        }
    }

