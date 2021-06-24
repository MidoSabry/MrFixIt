package com.example.fixawy.Client.CommentOfReply;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fixawy.Pojos.Comment;
import com.example.fixawy.R;
import java.util.ArrayList;

public class CommentOfReplyAdapter extends RecyclerView.Adapter<com.example.fixawy.Client.CommentOfReply.CommentOfReplyAdapter.CommentsItemViewHolder> {

    ArrayList<Comment> comments = new ArrayList<>();
    Context context;
    String jobTitle;

    public void clear() {
        comments.clear();
    }

    public void add(Comment comment){
        comments.add(comment);
        notifyDataSetChanged();
    }

    public CommentOfReplyAdapter(Context context, String jobTitle) {
        this.context = context;
        this.jobTitle = jobTitle;
    }

    @NonNull
    @Override
    public com.example.fixawy.Client.CommentOfReply.CommentOfReplyAdapter.CommentsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,null,false);
        com.example.fixawy.Client.CommentOfReply.CommentOfReplyAdapter.CommentsItemViewHolder viewHolder = new com.example.fixawy.Client.CommentOfReply.CommentOfReplyAdapter.CommentsItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.fixawy.Client.CommentOfReply.CommentOfReplyAdapter.CommentsItemViewHolder holder, int position) {
        holder.textViewPhoneOfClient.setText(comments.get(position).getPhoneOfClient());
        holder.textViewPhoneOfWorker.setText(comments.get(position).getPhoneOfWorker());
        holder.textViewReply.setText(comments.get(position).getReply());
        holder.textViewComment.setText(comments.get(position).getComment());

        String phoneOfWorker = comments.get(position).getPhoneOfWorker();
        String phoneOfClient = comments.get(position).getPhoneOfClient();
        String reply = comments.get(position).getReply();
        String comment = comments.get(position).getComment();


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Reply" +reply + "and Comment is" + comment +"and Job is" +jobTitle +"and phone client is "+ phoneOfClient+ "and phone worker"+ phoneOfWorker, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentsItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewReply,textViewPhoneOfWorker,textViewComment,textViewPhoneOfClient;
        public View layout;
        public CommentsItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            textViewReply = itemView.findViewById(R.id.reply);
            textViewPhoneOfWorker = itemView.findViewById(R.id.phoneOfWorker);
            textViewComment = itemView.findViewById(R.id.comment);
            textViewPhoneOfClient = itemView.findViewById(R.id.phoneOfClient);

        }
    }
}


