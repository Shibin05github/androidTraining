package com.helloworld.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] arrayItems = {"Item 1","Item 2","Item 3","Item 4","Item 5","Item 6","Item 7",
                                    "Item 8","Item 9","Item 10","Item 11","Item 12","Item 13","Item 14",};
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent,false);
        ViewHolder vh =new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(arrayItems[position]);
    }

    @Override
    public int getItemCount() {
        return arrayItems.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public ViewHolder(View V){
            super(V);
            tvTitle = V.findViewById(R.id.tvTitle);
        }
    }
}

