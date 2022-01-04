package com.example.fcmandroidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlarmSettingActivity extends AppCompatActivity{

    Button alarmonoff;
    Switch onOffSwitch;

    private final String url = "http://192.168.1.2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);

        alarmonoff = findViewById(R.id.alarmonoff);

        onOffSwitch = (Switch)  findViewById(R.id.switch1);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("Switch State=" + isChecked);
                class Dclass extends AsyncTask<String,Void,String> {

                    protected void onPostExecute(String data){

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

                String finalUrl = "";
                if(isChecked){
                    finalUrl = url + "/project/set_alarm_on.php";
                } else {
                    finalUrl = url + "/project/set_alarm_off.php";
                }

                Dclass obj = new Dclass();
                obj.execute(finalUrl);

            }

        });


        class Dclass extends AsyncTask<String,Void,String> {

            protected void onPostExecute(String data){

                if(data.equals("0")){
                    alarmonoff.setText("OFF");
                    System.out.println("The received data is equal to 0, so off");
                    onOffSwitch.setChecked(false);
                } else {
                    alarmonoff.setText("ON");
                    System.out.println("The received data is equal to 1, so on");
                    onOffSwitch.setChecked(true);
                }
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

        String finalUrl = url + "/project/get_alarm_state.php";
        Dclass obj = new Dclass();
        obj.execute(finalUrl);

    }

    public void updateAlarmState(View view) {
        class Dclass extends AsyncTask<String,Void,String> {

            protected void onPostExecute(String data){

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

        String finalUrl = "";
        if(alarmonoff.getText().toString().equals("ON")){
            finalUrl = url + "/project/set_alarm_off.php";
            alarmonoff.setText("OFF");
            onOffSwitch.setChecked(false);

        } else {
            finalUrl = url + "/project/set_alarm_on.php";
            alarmonoff.setText("ON");
            onOffSwitch.setChecked(true);
        }

        Dclass obj = new Dclass();
        obj.execute(finalUrl);
    }
}