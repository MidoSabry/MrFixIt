package com.example.fixawy.Worker.HomePageWorker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.RequestedPage.RequestedAdapter;
import com.example.fixawy.Pojos.MakeOrder;
import com.example.fixawy.R;

import java.util.List;

public class RequestedItemRecyclerAdapter extends RecyclerView.Adapter<RequestedItemRecyclerAdapter.RequestedItemRecyclerViewHolder> {
    private Context context;
    List<MakeOrder>makeOrders;

    public RequestedItemRecyclerAdapter(Context context, List<MakeOrder> makeOrders) {
        this.context = context;
        this.makeOrders = makeOrders;
    }

    @NonNull
    @Override
    public RequestedItemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.requested_worker_item,null,false);
        RequestedItemRecyclerAdapter.RequestedItemRecyclerViewHolder viewHolder = new RequestedItemRecyclerAdapter.RequestedItemRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestedItemRecyclerViewHolder holder, int position) {



        holder.worker_clocktv.setText(makeOrders.get(position).getTime());
        holder.worker_datetv.setText(makeOrders.get(position).getDate());
        //holder.worker_name_clienttv.setText(makeOrders.get(position).get());
        holder.worker_kindjobtv.setText(makeOrders.get(position).getTypeOfOrder()+"");
        holder.worker_address_clientv.setText(makeOrders.get(position).getLocation());
        holder.worker_phone_client.setText(makeOrders.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        if(makeOrders == null){
            return 0;
        } else {
            return makeOrders.size();
        }
    }

    public class RequestedItemRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView worker_clocktv,worker_datetv,worker_name_clienttv,worker_kindjobtv,worker_address_clientv,worker_phone_client;
        Button worker_phone_btn,worker_chat_btn;
        public RequestedItemRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            worker_clocktv = itemView.findViewById(R.id.worker_requested_clock);
            worker_datetv = itemView.findViewById(R.id.worker_requested_date);
            worker_name_clienttv = itemView.findViewById(R.id.worker_name_of_client);
            worker_kindjobtv = itemView.findViewById(R.id.worker_requsted_kind_job);
            worker_address_clientv = itemView.findViewById(R.id.worker_requsted_address);
            worker_phone_client = itemView.findViewById(R.id.worker_requsted_phone);

            worker_phone_btn = itemView.findViewById(R.id.worker_request_call_button);
            worker_chat_btn = itemView.findViewById(R.id.worker_request_chat_button);
        }
    }
}
