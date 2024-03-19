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

public class BMIHistoryAdapter extends RecyclerView.Adapter<BMIHistoryAdapter.ViewHolder> {

    private List<User> userList;


    public void setData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_bmi_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.BMIData.setText(String.valueOf(user.getBmi()));
        holder.date.setText(user.getDateTime().toString());
        holder.status.setText(user.getStatus());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView BMIData;
        TextView date;
        TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BMIData = itemView.findViewById(R.id.BMIData);
            date = itemView.findViewById(R.id.date);
            status = itemView.findViewById(R.id.status);
        }


    }

}
