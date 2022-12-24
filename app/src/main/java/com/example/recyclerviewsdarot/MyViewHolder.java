package com.example.recyclerviewsdarot;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView numView;



    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        numView = itemView.findViewById(R.id.numTv);

    }
}
