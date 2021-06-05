package com.example.fixawy.Worker.JobAccepted;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Pojos.JobAccepted;
import com.example.fixawy.R;

import java.util.List;

public class JobAcceptedAdapter extends RecyclerView.Adapter<JobAcceptedAdapter.JobAcceptedItemRecyclerViewHolder> {
   private Context context;
    List<JobAccepted> jobAccepteds;

    public JobAcceptedAdapter(Context context, List<JobAccepted> jobAccepteds) {
        this.context = context;
        this.jobAccepteds = jobAccepteds;
    }
    @NonNull
    @Override
    public JobAcceptedItemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_accepted_item,null,false);
        JobAcceptedItemRecyclerViewHolder viewHolder = new JobAcceptedItemRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobAcceptedItemRecyclerViewHolder holder, int position) {

        holder.worker_accept_job_date.setText(jobAccepteds.get(position).getTime());
        holder.worker_accept_job_time.setText(jobAccepteds.get(position).getDate());
        holder.worker_accept_job_name_of_user.setText(jobAccepteds.get(position).getUserName());
        holder.worker_accept_job_address_of_user.setText(jobAccepteds.get(position).getLocation());
        holder.worker_accept_job_phone_of_user.setText(jobAccepteds.get(position).getPhone());


    }

    @Override
    public int getItemCount() {
        if(jobAccepteds == null){
            return 0;
        } else {
            return jobAccepteds.size();
        }
    }

    public class JobAcceptedItemRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView worker_accept_job_date;
        TextView worker_accept_job_time;
        TextView worker_accept_job_name_of_user;
        TextView worker_accept_job_address_of_user;
        TextView worker_accept_job_phone_of_user;

        Button worker_accept_phone_btn,worker_accept_chat_btn;
        public JobAcceptedItemRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            worker_accept_job_date = itemView.findViewById(R.id.worker_Accepted_Job_clock);
            worker_accept_job_time = itemView.findViewById(R.id.worker_Accepte_Job_date);
            worker_accept_job_name_of_user = itemView.findViewById(R.id.worker_Accepted_Job_name_of_client);
            worker_accept_job_address_of_user = itemView.findViewById(R.id.worker_Accepted_Job_requsted_address);
            worker_accept_job_phone_of_user = itemView.findViewById(R.id.worker_requsted_Accepted_Job_phone);

            worker_accept_phone_btn = itemView.findViewById(R.id.worker_request_Accepted_Job_call_button);
            worker_accept_chat_btn = itemView.findViewById(R.id.worker_Accepted_Job_request_chat_button);
        }
    }
}
