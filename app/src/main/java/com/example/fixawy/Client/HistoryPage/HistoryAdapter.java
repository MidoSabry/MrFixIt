package com.example.fixawy.Client.HistoryPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.RequestedPage.RequestedAdapter;
import com.example.fixawy.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder> {
    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,null,false);
        HistoryAdapter.HistoryItemViewHolder viewHolder = new HistoryAdapter.HistoryItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HistoryItemViewHolder extends RecyclerView.ViewHolder {
        public HistoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
