package com.example.assign2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context context;
    ArrayList std_id;
    ArrayList std_name;
    ArrayList std_city;
    ArrayList std_age;

    public CustomAdapter(Context context, ArrayList std_id, ArrayList std_name, ArrayList std_city, ArrayList std_age) {
        this.context = context;
        this.std_id = std_id;
        this.std_name = std_name;
        this.std_city = std_city;
        this.std_age = std_age;
    }

    public CustomAdapter() {

    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        holder.text_id.setText(std_id.get(position).toString());
        holder.text_name.setText(std_name.get(position).toString());
        holder.text_city.setText(std_city.get(position).toString());
        holder.text_age.setText(std_age.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return std_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_id;
        TextView text_name;
        TextView text_city;
        TextView text_age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_id = itemView.findViewById(R.id.tv_dispID);
            text_name = itemView.findViewById(R.id.tv_dispName);
            text_city = itemView.findViewById(R.id.tv_dispCity);
            text_age = itemView.findViewById(R.id.tv_dispAge);
        }
    }
}

