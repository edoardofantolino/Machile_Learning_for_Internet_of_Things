package com.example.fcmandroidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class HistoryActivity extends AppCompatActivity {

    private TextView history;

    public String[] records;

    private final String url = "http://192.168.1.2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        history = findViewById(R.id.history);

        history.setText("Loading History");


        class Dclass extends AsyncTask<String,Void,String> {

            protected void onPostExecute(String data){
                jsonParserData(data);

                String final_string = "";
                int i;
                for(i=0; i<records.length; i++){

                    java.util.Date time = new java.util.Date((long)Integer.parseInt(records[i].split(",")[3])*1000);

                    String[] timeString = time.toString().split(" ");
                    String final_date = timeString[2] + " " + timeString[1] + " " + timeString[5] + " " + timeString[3];

                    final_string = final_string + final_date + " " + records[i].split(",")[1] + "\n";
                }
                history.setText(final_string);
            }

            @Override
            protected String doInBackground(String... param) {

                try{
                    URL url = new URL(param[0]);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    return br.readLine();
                }catch(Exception ex){
                    return ex.getMessage();
                }
            }
        }


        String finalUrl = url + "/project/read_value.php";
        Dclass obj = new Dclass();
        obj.execute(finalUrl);

    }


    public void jsonParserData(String data){

        System.out.println("In the jsonParserData");
        System.out.println(data.toString());

        records = data.split(" ");

    }
}