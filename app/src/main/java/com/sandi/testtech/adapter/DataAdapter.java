package com.sandi.testtech.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;
import com.sandi.testtech.DetailActivity;
import com.sandi.testtech.R;
import com.sandi.testtech.model.DataExample;

import org.w3c.dom.Text;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private List<DataExample> data;
    private Context context;

    public DataAdapter(List<DataExample> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataExample item = data.get(position);
        holder.id.setText("Id : "+ item.getId());
        holder.title.setText("Title : " + item.getTitle());

        holder.cardView.setOnClickListener(v -> {
            Hawk.init(context).build();
            Hawk.put("contentData", item);
            Intent toMovie = new Intent(context, DetailActivity.class);
            context.startActivity(toMovie);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void refreshData(List<DataExample> newData) {
        data = newData;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id);
            title = itemView.findViewById(R.id.tv_title);
            cardView = itemView.findViewById(R.id.cv_content);
        }
    }
}

