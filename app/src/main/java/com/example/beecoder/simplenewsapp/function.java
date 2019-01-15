package com.example.beecoder.simplenewsapp;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

/**
 * Created by Muhammad Samlan Abid on 14/01/2019.
 */
//function.java berfungsi untuk membuat method seperti Internet Connection Checker dan eksekusi URLnya
public class function {

    public static boolean isNetworkAvailable(Context context)
    {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public static String executeGet(String targetURL, String urlparameter){
        URL url;
        HttpURLConnection connection = null;
        try {
            //Membuat koneksinya
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            //connection.setRequestMethod("POST");
            //connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("content-type", "application/json;  charset=utf-8");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(false);

            InputStream is;
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK){
                is = connection.getErrorStream();
            } else {
                is = connection.getInputStream();
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line =  rd.readLine()) != null){
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e){
            return null;
        } finally {
            if(connection != null ){
                connection.disconnect();
            }
        }
    }
}
