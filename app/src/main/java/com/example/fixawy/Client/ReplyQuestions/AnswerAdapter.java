package com.example.fixawy.Client.ReplyQuestions;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.fixawy.Share.VerifyCode.VerificationCode.EXTR_USER_NAME;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.CommentOfReply.CommentOfReplyActivity;
import com.example.fixawy.Client.CommentsFromAllClients.CommentsFromAllClientsActivity;
import com.example.fixawy.Pojos.Answer;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.Pojos.Reply;
import com.example.fixawy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.PreviousQuestionItemViewHolder> {

    ArrayList<Answer> answers = new ArrayList<>();
    Context context;
    String jobTitle;
    String phoneClient,clientName;

    public void clear() {
        answers.clear();
    }

    public void add(Answer ans){
        answers.add(ans);
        notifyDataSetChanged();
    }

    public AnswerAdapter(Context context, String jobTitle,String phoneClient,String clientName) {
        this.context = context;
        this.jobTitle = jobTitle;
        this.phoneClient = phoneClient;
        this.clientName = clientName;
    }


    @NonNull
    @Override
    public AnswerAdapter.PreviousQuestionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item,null,false);
        AnswerAdapter.PreviousQuestionItemViewHolder viewHolder = new AnswerAdapter.PreviousQuestionItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerAdapter.PreviousQuestionItemViewHolder holder, int position) {
        holder.textViewAnswers.setText(answers.get(position).getReplay());
        holder.textViewPhone.setText(answers.get(position).getPhone());
        holder.textViewQuestion.setText(answers.get(position).getClientQuestion());
       // holder.textViewPhoneOfClient.setText(answers.get(position).getPhoneOfClient());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneWorker = answers.get(position).getPhone();
                String reply = answers.get(position).getReplay();
                String phoneOfCard = answers.get(position).getPhoneOfClient();
                Toast.makeText(v.getContext(),phoneWorker, Toast.LENGTH_SHORT).show();
                Toast.makeText(v.getContext(), jobTitle, Toast.LENGTH_SHORT).show();
                Toast.makeText(v.getContext(), phoneClient, Toast.LENGTH_SHORT).show();
             /*   v.getContext().startActivity(new Intent(v.getContext(), CommentsFromAllClientsActivity.class)
                        .putExtra("phoneWorker",phoneWorker)
                        .putExtra("phoneClient",phoneClient)
                        .putExtra("jobTitle",jobTitle)
                        .putExtra("reply",reply)
                        .putExtra(EXTR_USER_NAME,clientName)
                        .putExtra("phoneOfCard",phoneOfCard));*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class PreviousQuestionItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAnswers,textViewPhone,textViewQuestion;
       // TextView textViewPhoneOfClient;
        public View layout;
        public PreviousQuestionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            textViewAnswers = itemView.findViewById(R.id.answer);
            textViewPhone = itemView.findViewById(R.id.phone);
            textViewQuestion = itemView.findViewById(R.id.question);
         //   textViewPhoneOfClient = itemView.findViewById(R.id.phoneOfClient);
        }
    }
}

