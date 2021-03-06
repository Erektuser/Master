package tkacz.kamil.masterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ConnectionLayout extends AppCompatActivity {

    EditText editIp;
    Button btnOn, btnOff, btnconnection;
    TextView textInfo1, textInfo2, textInfo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_layout);

        editIp = (EditText)findViewById(R.id.ip);
        //btnconnection = (Button)findViewById(R.id.bconnect);
        btnOn = (Button)findViewById(R.id.bon);
        btnOff = (Button)findViewById(R.id.boff);
        textInfo1 = (TextView)findViewById(R.id.info1);
        textInfo2 = (TextView)findViewById(R.id.info2);
        textInfo3 = (TextView)findViewById(R.id.info3);



        btnOn.setOnClickListener(btnOnOffClickListener);
        btnOff.setOnClickListener(btnOnOffClickListener);
        //btnconnection.setOnClickListener(btnConnection);
    }


    View.OnClickListener btnOnOffClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String onoff;
            if (v == btnOn) onoff = "/on";
            else onoff = "/off";

            btnOn.setEnabled(false);
            btnOff.setEnabled(false);

            //String serverIP = editIp.getText().toString() + onoff;
            String serverIP = "192.168.0.249:8080"+onoff;
            TaskEsp taskEsp = new TaskEsp(serverIP);
            taskEsp.execute();
        }

    };

    private class TaskEsp extends AsyncTask<Void, Void, String> {

        String server;

        TaskEsp(String server){
            this.server = server;
        }

        @Override
        protected String doInBackground(Void... params) {

            final String p = "http://" + server;

/*              runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textInfo1.setText(p);
                }
            });*/

            String serverResponse = "";

            //Using java.net.HttpURLConnection
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) (new URL(p).openConnection());

                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    InputStream inputStream = null;
                    inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader =
                            new BufferedReader(new InputStreamReader(inputStream));
                    serverResponse = bufferedReader.readLine();

                    inputStream.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            }

            return serverResponse;
        }



        @Override
        protected void onPostExecute(String s) {
            textInfo2.setText(s);
            btnOn.setEnabled(true);
            btnOff.setEnabled(true);
        }
    }
}
