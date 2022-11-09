package com.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.Adapters.MyAdapter;

public class RecyclerViewHorizontalActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    Button btnShow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_horizontal);
        mRecycler = findViewById(R.id.myReCyclrView);
        MyAdapter myAdapter = new MyAdapter();
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(RecyclerViewHorizontalActivity.this,RecyclerView.HORIZONTAL,false);
        mRecycler.setLayoutManager(myLayoutManager);
        mRecycler.setAdapter(myAdapter);
        btnShow = findViewById(R.id.btnShowItem);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecyclerViewHorizontalActivity.this, "Selected item is", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
