package com.example.fixawy.Client.HistoryPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.RequestedPage.RequestedAdapter;
import com.example.fixawy.Pojos.Answer;
import com.example.fixawy.Pojos.ClientHistory;
import com.example.fixawy.R;
import com.example.fixawy.Worker.WorkerQuestions.WorkerQuestionsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder> {

    ArrayList<ClientHistory> clientHistories = new ArrayList<>();
    DatabaseReference reference;
    Context context;
    String phoneClient;
    AlertDialog alertDialog;
    LayoutInflater inflater;
    Button btnDelete,btnCancel;
    FirebaseDatabase db ;

    public HistoryAdapter(HistoryActivity historyActivity, String phoneClient) {
        this.context = historyActivity;
        this.phoneClient = phoneClient;

    }

    public void clear() {
        clientHistories.clear();
    }

    public void get(ClientHistory clientHistory){
        clientHistories.add(clientHistory);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,null,false);
        HistoryAdapter.HistoryItemViewHolder viewHolder = new HistoryAdapter.HistoryItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, int position) {
        holder.textViewTime.setText(clientHistories.get(position).getTime());
        holder.textViewDate.setText(clientHistories.get(position).getDate());
        holder.textViewEmpName.setText(clientHistories.get(position).getUserName());
        holder.textViewTypeOfJob.setText(clientHistories.get(position).getTypeOfOrder());
        holder.ratingBar.setRating(3);
        String phoneWorker = clientHistories.get(position).getPhone();
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
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
                        ClientHistory clientHistory = clientHistories.get(position);
                        clientHistories.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(v.getContext(), "delete", Toast.LENGTH_SHORT).show();
                        reference = FirebaseDatabase.getInstance().getReference().child("Client").child("Data");
                        reference.child(phoneClient).child("History Jobs").child(phoneWorker).setValue(null);

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
        return clientHistories.size();
    }

    public class HistoryItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTime,textViewDate,textViewEmpName,textViewTypeOfJob;
        RatingBar ratingBar;
        Button buttonDelete;
        public View layout;
        public HistoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            textViewDate = itemView.findViewById(R.id.history_worker_date);
            textViewTime = itemView.findViewById(R.id.history_worker_clock);
            textViewEmpName = itemView.findViewById(R.id.history_user_name);
            textViewTypeOfJob = itemView.findViewById(R.id.history_kind_job);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            buttonDelete = itemView.findViewById(R.id.delete);

        }
    }
}
