package com.example.fixawy.Client.SelectedPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.RequestedPage.RequestedAdapter;
import com.example.fixawy.Pojos.Accepted;
import com.example.fixawy.Pojos.MakeOrder;
import com.example.fixawy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SelectedAdapter extends RecyclerView.Adapter<SelectedAdapter.SelectedItemViewHolder> {
    private Context context;
    List<Accepted>accepteds;

    public SelectedAdapter(Context context, List<Accepted> accepteds) {
        this.context = context;
        this.accepteds = accepteds;
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
        TextView worker_name,worker_phone,worker_comment;
        public SelectedItemViewHolder(@NonNull View itemView) {
            super(itemView);
            worker_selected_imageView = itemView.findViewById(R.id.worker_img);
            worker_name = itemView.findViewById(R.id.worker_name);
            worker_phone = itemView.findViewById(R.id.worker_phone);
            worker_comment = itemView.findViewById(R.id.worker_description);

        }
    }
}
