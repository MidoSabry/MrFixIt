package com.example.fixawy.Client.SelectedPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.RequestedPage.RequestedAdapter;
import com.example.fixawy.R;

public class SelectedAdapter extends RecyclerView.Adapter<SelectedAdapter.SelectedItemViewHolder> {
    @NonNull
    @Override
    public SelectedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_item,null,false);
        SelectedAdapter.SelectedItemViewHolder viewHolder = new SelectedAdapter.SelectedItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SelectedItemViewHolder extends RecyclerView.ViewHolder {
        public SelectedItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
