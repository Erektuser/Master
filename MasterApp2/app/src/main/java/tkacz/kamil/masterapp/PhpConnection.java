package tkacz.kamil.masterapp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PhpConnection /*extends AsyncTask<JSONObject, JSONObject, JSONObject>*/{

    String myurl = new String("http://192.168.0.249:8080/test.php");


   /* protected JSONObject doInBackground(JSONObject... parameters){



    }*/



    public void postJSONObject(String myurl, JSONObject parameters){
        OutputStream os = null;
        InputStream is = null;
        HttpURLConnection conn = null;

        try {

            URL url = new URL(myurl);

            String message = parameters.toString();

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout( 10000 /*milliseconds*/ );
            conn.setConnectTimeout( 15000 /* milliseconds */ );
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            //conn.setFixedLengthStreamingMode(message.getBytes().length);

            conn.connect();

            //setup send
            os = new BufferedOutputStream(conn.getOutputStream());
            //os.write(message.getBytes());
            os.write(1231312);
            //clean up
            os.flush();

            //String contentAsString = readIt(is,len);
        } catch (IOException e) {
            e.printStackTrace();
        }
         finally {
            //clean up
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            conn.disconnect();
        }

    };


    }










    /*public static String postJSONObject(String myurl, JSONObject parameters) {
        HttpURLConnection conn = null;
        try {
            StringBuffer response = null;
            URL url = new URL(myurl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            OutputStream out = new BufferedOutputStream(conn.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(parameters.toString());
            writer.close();
            out.close();
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode" + responseCode);
            switch (responseCode) {
                case 200:
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return response.toString();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }*/



