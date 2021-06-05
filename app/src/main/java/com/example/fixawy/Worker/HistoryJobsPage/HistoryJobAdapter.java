package com.example.fixawy.Worker.HistoryJobsPage;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Pojos.HistoryWorker;
import com.example.fixawy.Pojos.MakeOrder;
import com.example.fixawy.R;

import java.util.List;


public class HistoryJobAdapter extends RecyclerView.Adapter<HistoryJobAdapter.HistoryJobsItemViewHolder> {
    List<HistoryWorker>historyWorkerList;
    Context context;

    public HistoryJobAdapter(Context context, List<HistoryWorker> historyWorkerList) {
        this.context = context;
        this.historyWorkerList = historyWorkerList;
    }

    @NonNull
    @Override
    public HistoryJobAdapter.HistoryJobsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,null,false);
        HistoryJobAdapter.HistoryJobsItemViewHolder viewHolder = new HistoryJobAdapter.HistoryJobsItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryJobAdapter.HistoryJobsItemViewHolder holder, int position) {
        holder.textViewClock.setText(historyWorkerList.get(position).getTime());
        holder.textViewDate.setText(historyWorkerList.get(position).getDate());
        holder.textViewUserAddress.setText(historyWorkerList.get(position).getAddress());
        holder.textViewUserName.setText(historyWorkerList.get(position).getName());
        holder.textViewUserPhone.setText(historyWorkerList.get(position).getPhone());

    }
    @Override
    public int getItemCount() {
        if(historyWorkerList == null){
            return 0;
        } else {
            return historyWorkerList.size();
        }
    }

    public class HistoryJobsItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewClock,textViewDate,textViewUserName,textViewUserAddress,textViewUserPhone;
        public HistoryJobsItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewClock=itemView.findViewById(R.id.history_worker_clock);
            textViewDate=itemView.findViewById(R.id.history_worker_date);
            textViewUserName=itemView.findViewById(R.id.history_user_name);
            textViewUserAddress=itemView.findViewById(R.id.history_user_address);
            textViewUserPhone=itemView.findViewById(R.id.history_user_phone);

        }
    }
}