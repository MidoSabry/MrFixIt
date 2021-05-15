package com.example.fixawy.Client.RequestedPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.HomePageClient.EmployeeItemRecyclerAdapter;
import com.example.fixawy.R;

public class RequestedAdapter extends RecyclerView.Adapter<RequestedAdapter.RequestedItemViewHolder> {

    private Context context;

    @NonNull
    @Override
    public RequestedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.requested_item,null,false);
        RequestedItemViewHolder viewHolder = new RequestedItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestedItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class RequestedItemViewHolder extends RecyclerView.ViewHolder{

        public RequestedItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
