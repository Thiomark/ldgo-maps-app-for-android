package com.example.ldgo.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ldgo.R;
import com.example.ldgo.entities.Address;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<Address> searches;

    public RecyclerAdapter(ArrayList<Address> searches){
        this.searches = searches;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt;

        MyViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.name);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String name = searches.get(position).getFormatted_address();
        holder.nameTxt.setText(name);
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }
}
