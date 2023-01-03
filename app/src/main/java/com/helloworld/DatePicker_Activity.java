package com.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.helloworld.Utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePicker_Activity extends AppCompatActivity {

    private TextView tvSelectDate;
    private TextView tvSelectTime;
    private TextView tvShowResult;
    private Dialog dialog;
    private Button btnShowSnack;
    private Button btnAPI_Call;
    private RelativeLayout rl ;
    private EditText etCityName;
    String cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        tvSelectDate = findViewById(R.id.tvDatePicker);
        tvSelectTime = findViewById(R.id.tvTimePicker);
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        AppCompatButton btnDialog  = findViewById(R.id.btnSHowProDialg);
        btnShowSnack=findViewById(R.id.btnShowSnak);
        rl = findViewById(R.id.relativeLayout);
        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DatePicker_Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        month=month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        tvSelectDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        tvSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(DatePicker_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minute) {
                        calendar.set(0,0,0,hoursOfDay,minute);
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm aa");
                        tvSelectTime.setText("Time: "+simpleDateFormat.format(calendar.getTime()));
                    }
                },12,0,false);
                timePickerDialog.show();
            }
        });

        dialog = new Dialog(DatePicker_Activity.this,R.style.MyAlertDialogTheme);
        dialog.setContentView(R.layout.pb_custom);
        dialog.setCanceledOnTouchOutside(false);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.show();
            }
        });

        btnShowSnack.setOnClickListener(View ->{
            Snackbar.make(rl,"This is a snackbar",Snackbar.LENGTH_INDEFINITE)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(Color.YELLOW)
                    .setAction("Create Toast", new View.OnClickListener() {
                        @Override
                        public void onClick(android.view.View view) {
                            Toast.makeText(DatePicker_Activity.this, "This is a toast", Toast.LENGTH_SHORT).show();
                        }
                    }).setActionTextColor(Color.BLACK)
                    .show();
        });

        btnAPI_Call = findViewById(R.id.btnCallAPI);
        etCityName = findViewById(R.id.etText);
        tvShowResult = findViewById(R.id.tvResult);
        btnAPI_Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnCallAPI){
                     cityName = etCityName.getText().toString();
                    try {
                        getData();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
}

    public void getData() throws MalformedURLException {
        Uri uri = Uri.parse("https://datausa.io/api/data?drilldowns=State&measures=Population&year=latest").buildUpon().build();
        URL url = new URL(uri.toString());
        new DoTask().execute(url);
    }
    class DoTask extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String data =null;
            try {
                data = NetworkUtils.makeHTTPRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                parseJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        public void parseJson(String data) throws JSONException {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray cityArray = jsonObject.getJSONArray("data");

            for (int i = 0; i<cityArray.length(); i++){
                JSONObject cityObj = cityArray.getJSONObject(i);
                String ctName = cityObj.get("State").toString();
                if (ctName.equals(cityName)){
                    String population = cityObj.get("Population").toString();
                    tvShowResult.setText(population);
                    break;
                }else {
                    tvShowResult.setText("not found");
                }
            }
        }
    }
}

