package tkacz.kamil.masterapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    PhpConnection phpConnection;
    JSONObject obj = new JSONObject();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            obj.put("name", "mkyong.com");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        new AsyncTask<Integer, Void, Void>(){
            protected Void doInBackground(Integer... params) {

                OutputStream os = null;
                InputStream is = null;
                HttpURLConnection conn = null;

                try {

                    URL url = new URL("http://192.168.0.249:8080/test.php");

                    String message = obj.toString();

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
                // **Code**
                return null;
            }

        };





        ConstraintLayout rlayout = (ConstraintLayout) findViewById(R.id.activity_main);
        rlayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent in = new Intent(MainActivity.this, MasterTitleLayout.class);
                startActivity(in);
            }


        });





    };
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
