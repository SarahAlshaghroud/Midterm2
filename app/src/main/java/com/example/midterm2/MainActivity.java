package com.example.midterm2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private TextView reservation;
    TextView temperature,humidity,description;
    String weatherWebserviceURL, url;
    Spinner location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toast.makeText(MainActivity.this,"Welcome to the project of Sarah Alshaghroud, 201520",Toast.LENGTH_SHORT).show();
        reservation=(TextView) findViewById(R.id.txtDate);
        Button btn=(Button) findViewById(R.id.button);
        location=(Spinner) findViewById(R.id.spinner) ;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,d,c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q=london&appid=1f1d6588819771e3d082ba37cbf28916&units=metric";

        temperature = (TextView) findViewById(R.id.temprature);
        humidity=(TextView) findViewById(R.id.humidty);
        description = (TextView) findViewById(R.id.Des);

        weather(weatherWebserviceURL);

        Button btnNext=(Button) findViewById(R.id.button2);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });


    }

    public void weather(String url) {
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Sarah","Succeeded retrieving URL");
                        Log.d("Sarah", response.toString());

                        try {
                            String town=response.getString("name");
                            Log.d("Sarah-town",town);
                            description.setText(town);

                            //nested object
                            JSONObject jsonMain=response.getJSONObject("main");

                            double temp=jsonMain.getDouble("temp");

                            Log.d("Sarah-temp",String.valueOf(temp));
                            temperature.setText(temp+"°C");


                            JSONObject jsonM=response.getJSONObject("main");

                            double humid=jsonM.getDouble("humidity");

                            Log.d("Sarah-humidity",String.valueOf(humid));
                            humidity.setText("Humidity: "+humid+"%");

                            }
                        catch (JSONException e){
                            e.printStackTrace();
                            Log.d("Receive Errror",e.toString());
                        }

                        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if(i==0) {
                                    String url="https://api.openweathermap.org/data/2.5/weather?q=paris&appid=1f1d6588819771e3d082ba37cbf28916&units=metric";
                                    weather(url);
                                }
                                else if(i==1) {
                                    String url="https://api.openweathermap.org/data/2.5/weather?q=riyadh&appid=1f1d6588819771e3d082ba37cbf28916&units=metric";
                                    weather(url);
                                }
                                else if(i==2) {
                                    String url="https://api.openweathermap.org/data/2.5/weather?q=jeddah&appid=1f1d6588819771e3d082ba37cbf28916&units=metric";

                                    weather(url);
                                }
                            }


                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Sarah","Error retrieving URL");
            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);

    }

    Calendar c =Calendar.getInstance();
    DateFormat fmtDate=DateFormat.getDateInstance();
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            reservation.setText("The Date picked is  "+fmtDate.format(c.getTime()));  }};
}