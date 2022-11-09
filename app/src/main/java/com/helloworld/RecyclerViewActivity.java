package com.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.helloworld.Adapters.MyAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecycler = findViewById(R.id.myReCyclrView);
        Button btnList = findViewById(R.id.btnList);
        Button btnGrid = findViewById(R.id.btnGrid);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAdapterList();
            }
        });

        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAdapterGrid();
            }
        });
    }

    private void setAdapterList(){
        MyAdapter myAdapter = new MyAdapter();
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(RecyclerViewActivity.this);
        mRecycler.setLayoutManager(myLayoutManager);
        mRecycler.setAdapter(myAdapter);
    }
    private void setAdapterGrid(){
        MyAdapter myAdapter = new MyAdapter();
        GridLayoutManager myLayoutManager = new GridLayoutManager(RecyclerViewActivity.this,2);
        mRecycler.setLayoutManager(myLayoutManager);
        mRecycler.setAdapter(myAdapter);
    }
}