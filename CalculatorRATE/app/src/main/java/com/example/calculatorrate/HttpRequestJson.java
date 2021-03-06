package com.example.calculatorrate;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequestJson extends AsyncTask<String, Void,String> {


    private URL url;
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    @Override
    protected String doInBackground(String... strings) {

        StringBuilder stringBuilder = new StringBuilder();

        try{
             url = new URL(strings[0]);
             httpURLConnection = (HttpURLConnection) url.openConnection();
             inputStream = httpURLConnection.getInputStream();
             inputStreamReader = new InputStreamReader(inputStream);
             bufferedReader = new BufferedReader(inputStreamReader);

             String line ;

             while ((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return stringBuilder.toString();
    }
}
