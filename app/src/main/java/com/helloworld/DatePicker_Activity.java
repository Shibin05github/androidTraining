package com.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePicker_Activity extends AppCompatActivity {

    private TextView tvSelectDate;
    private TextView tvSelectTime;
    private Dialog dialog;
    private Button btnShowSnack;
    private RelativeLayout rl ;

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
    }
}

