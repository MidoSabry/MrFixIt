package com.example.fixawy.Worker.HistoryJobsPage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.R;


public class HistoryJobAdapter extends RecyclerView.Adapter<HistoryJobAdapter.HistoryJobsItemViewHolder> {
    @NonNull
    @Override
    public HistoryJobAdapter.HistoryJobsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,null,false);
        HistoryJobAdapter.HistoryJobsItemViewHolder viewHolder = new HistoryJobAdapter.HistoryJobsItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryJobAdapter.HistoryJobsItemViewHolder holder, int position) {
        holder.textViewClock.setText("1:34");
        holder.textViewDate.setText("22-5-2021");
        holder.textViewName.setText("Walaa Ahmed Mohamed");
        holder.textViewJobType.setText("Maintenance and repair");
        holder.textViewAddress.setText("Sidi Basher - Alex");
    }
    @Override
    public int getItemCount() {
        return 5;
    }

    public class HistoryJobsItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewClock,textViewDate,textViewName,textViewJobType,textViewAddress;
        public HistoryJobsItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewClock=itemView.findViewById(R.id.history_clock);
            textViewDate=itemView.findViewById(R.id.history_date);
            textViewName=itemView.findViewById(R.id.emp_doneby);
            textViewJobType=itemView.findViewById(R.id.history_kind_job);
            textViewAddress=itemView.findViewById(R.id.job_address);

        }
    }
}