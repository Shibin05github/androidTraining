package com.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView lstView = findViewById(R.id.lvItems);
        final ArrayList<String> cellPhones = new ArrayList<>();
        cellPhones.add("Iphone");
        cellPhones.add("Samsung");
        cellPhones.add("Xiami");
        cellPhones.add("Lenovo");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cellPhones);
        lstView.setAdapter(arrayAdapter);


    }
}