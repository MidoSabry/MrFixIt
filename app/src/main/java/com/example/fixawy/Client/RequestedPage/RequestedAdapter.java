package com.example.fixawy.Client.RequestedPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.HomePageClient.OnItemClick;
import com.example.fixawy.Client.MakeOrder.Repo.ClientOrderRepo;
import com.example.fixawy.Client.MakeOrder.pojos.OrderTree;
import com.example.fixawy.Pojos.ClientHistory;
import com.example.fixawy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RequestedAdapter extends RecyclerView.Adapter<RequestedAdapter.RequestedItemViewHolder> {
     Context context;
    List<OrderTree> orderTreeItems;
   onitemclick onItemClick;
    RequestedPageViewModel requestedPageViewModel;
     OrderTree orderTree;
    DatabaseReference reference;
    FirebaseDatabase db ;
    List<String>uIds;
    AlertDialog alertDialog;
    LayoutInflater inflater;
    Button btnDelete,btnCancel;

    public RequestedAdapter(Context context, List<OrderTree> orderTreeItems,onitemclick onItemClick) {
        this.context = context;
        this.orderTreeItems = orderTreeItems;
        this.onItemClick = onItemClick;
    }

    public RequestedAdapter(RequestedActivity requestedActivity, List<String> uIds) {
        this.context = requestedActivity;
        this.uIds = uIds;
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

                onItemClick.onclick(position,0);
            }
        });
        db = FirebaseDatabase.getInstance();
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(v.getContext()).create();
                inflater = LayoutInflater.from(v.getContext());
                View dialogView = inflater.inflate(R.layout.delete_dialog, null);
                btnDelete= dialogView.findViewById(R.id.btnDelete);
                btnCancel=dialogView.findViewById(R.id.btnCancel);
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Client");
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      onItemClick.onclick(position,1);


                        /*Toast.makeText(v.getContext(), "delete", Toast.LENGTH_SHORT).show();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final DatabaseReference myRef = database.getReference("Client").child("make order").child(orderTreeItems.get(position).getPhone()).child(orderTreeItems.get(position).getJobTitle()).child("order Details").child(String.valueOf(uIds));
                        myRef.setValue(null);*/

                        alertDialog.cancel();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {

                        alertDialog.cancel();
                    }
                });
                alertDialog.setView(dialogView);
                alertDialog.show();

            }
        });


    }

    @Override
    public int getItemCount() {

        return orderTreeItems.size();
    }

    class RequestedItemViewHolder extends RecyclerView.ViewHolder {
        TextView jobTitle, typeOfOrder, location, phone, date, time;
        Button request_btn,delete_btn;
        public RequestedItemViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitle = itemView.findViewById(R.id.requested_job_title);
            typeOfOrder = itemView.findViewById(R.id.requsted_kind_job);
            location = itemView.findViewById(R.id.requsted_address);
            phone = itemView.findViewById(R.id.requsted_phone);
            date = itemView.findViewById(R.id.requested_date);
            time = itemView.findViewById(R.id.requested_clock);
            request_btn = itemView.findViewById(R.id.request_edit_button);
            delete_btn = itemView.findViewById(R.id.request_delete_button);
        }

    }


}
