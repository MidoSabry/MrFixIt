package com.example.fixawy.Client.RequestedPage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.EditPage.EditActivity;
import com.example.fixawy.Client.EditPage.EditActivityViewModel;
import com.example.fixawy.Client.HomePageClient.EmployeeItemRecyclerAdapter;
import com.example.fixawy.Client.MakeOrder.pojos.OrderTree;
import com.example.fixawy.Pojos.AllCategory;
import com.example.fixawy.R;

import java.util.ArrayList;
import java.util.List;

public class RequestedAdapter extends RecyclerView.Adapter<RequestedAdapter.RequestedItemViewHolder> {
     Context context;
    List<OrderTree> orderTreeItems;
    onItemClick onItemClick;


    public RequestedAdapter(Context context, List<OrderTree> orderTreeItems,onItemClick onItemClick) {
        this.context = context;
        this.orderTreeItems = orderTreeItems;
        this.onItemClick = onItemClick;
    }





    @NonNull
    @Override
    public RequestedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.requested_item, null, false);
        RequestedItemViewHolder viewHolder = new RequestedItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestedItemViewHolder holder, int position) {
        holder.jobTitle.setText(orderTreeItems.get(position).getJobTitle());
      holder.typeOfOrder.setText(orderTreeItems.get(position).getTypeOfOrder());
        holder.location.setText(orderTreeItems.get(position).getLocation());
        holder.phone.setText(orderTreeItems.get(position).getPhone());
        holder.time.setText(orderTreeItems.get(position).getTime());
        holder.date.setText(orderTreeItems.get(position).getDate());
        holder.request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClick.onItemClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderTreeItems.size();
    }

    class RequestedItemViewHolder extends RecyclerView.ViewHolder {
        TextView jobTitle, typeOfOrder, location, phone, date, time;
        Button request_btn;
        public RequestedItemViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitle = itemView.findViewById(R.id.requested_job_title);
            typeOfOrder = itemView.findViewById(R.id.requsted_kind_job);
            location = itemView.findViewById(R.id.requsted_address);
            phone = itemView.findViewById(R.id.requsted_phone);
            date = itemView.findViewById(R.id.requested_date);
            time = itemView.findViewById(R.id.requested_clock);
            request_btn = itemView.findViewById(R.id.request_edit_button);
        }

    }


}
