package com.example.fixawy.Client.SelectedPage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.AcceptedWorkerPage.AcceptedWorkActivity;
import com.example.fixawy.Client.ReplyQuestions.AnswerActivity;
import com.example.fixawy.Client.RequestedPage.RequestedAdapter;
import com.example.fixawy.Pojos.Accepted;
import com.example.fixawy.Pojos.MakeOrder;
import com.example.fixawy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SelectedAdapter extends RecyclerView.Adapter<SelectedAdapter.SelectedItemViewHolder> {
    private Context context;
    List<Accepted>accepteds;
    String phoneWorkerNum,phoneClientNum,workerJobTitle,nameOfWorker;



    public SelectedAdapter(Context context, List<Accepted> accepteds,String phoneClientNum) {
        this.context = context;
        this.accepteds = accepteds;
        this.phoneClientNum = phoneClientNum;
    }
    @NonNull
    @Override
    public SelectedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_item,null,false);
        SelectedAdapter.SelectedItemViewHolder viewHolder = new SelectedAdapter.SelectedItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedItemViewHolder holder, int position) {
        Picasso.get().load(accepteds.get(position).getImage()).placeholder(R.drawable.person).into(holder.worker_selected_imageView);
        holder.worker_name.setText(accepteds.get(position).getNameOfWorker());
        holder.worker_phone.setText(accepteds.get(position).getPhoneOfWorker());
        holder.worker_comment.setText(accepteds.get(position).getCommentLine());
        holder.worker_job_title.setText(accepteds.get(position).getJobTitle());



        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneWorkerNum = accepteds.get(position).getPhoneOfWorker();
                workerJobTitle = accepteds.get(position).getJobTitle();
                nameOfWorker = accepteds.get(position).getNameOfWorker();
                v.getContext().startActivity(new Intent(v.getContext(), AcceptedWorkActivity.class)
                        .putExtra("phoneWorker",phoneWorkerNum)
                        .putExtra("phoneClient",phoneClientNum)
                        .putExtra("workerJobTitle",workerJobTitle)
                        .putExtra("nameOfWorker",nameOfWorker));

                Toast.makeText(v.getContext(),phoneClientNum, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        if(accepteds == null){
            return 0;
        } else {
            return accepteds.size();
        }
    }

    public class SelectedItemViewHolder extends RecyclerView.ViewHolder {

        ImageView worker_selected_imageView;
        TextView worker_name,worker_phone,worker_comment,worker_job_title;
        public View layout;
        public SelectedItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            worker_selected_imageView = itemView.findViewById(R.id.worker_img);
            worker_name = itemView.findViewById(R.id.worker_name);
            worker_phone = itemView.findViewById(R.id.worker_phone);
            worker_comment = itemView.findViewById(R.id.worker_description);
            worker_job_title = itemView.findViewById(R.id.worker_job_title);


        }
    }
}