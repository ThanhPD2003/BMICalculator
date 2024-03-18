package com.example.bmicalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmicalculator.models.User;

import java.util.List;

public class WeightHistoryAdapter extends RecyclerView.Adapter<WeightHistoryAdapter.ViewHolder> {

    private List<User> userList;


    public void setData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_weight_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);


        holder.weightData.setText(String.valueOf(user.getWeight()));
        holder.date.setText(user.getDateTime().toString());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView weightData;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weightData = itemView.findViewById(R.id.weightData);
            date = itemView.findViewById(R.id.date);
        }


    }

}
