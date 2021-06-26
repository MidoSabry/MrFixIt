package com.example.fixawy.Worker.ReplayQuestionsPage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import static com.example.fixawy.Share.VerifyCode.VerificationCode.EXTR_USER_NAME;
import com.example.fixawy.Client.CommentsFromAllClients.CommentsFromAllClientsActivity;
import com.example.fixawy.Pojos.Answer;
import com.example.fixawy.R;
import com.example.fixawy.Worker.CommentsForWorker.CommentsForWorkerActivity;

import java.util.ArrayList;

public class ReplyQuestionsAdapter extends RecyclerView.Adapter<ReplyQuestionsAdapter.PreviousQuestionItemViewHolder> {

    ArrayList<Answer> answers = new ArrayList<>();
    Context context;
    String jobTitle;
    String phoneNumOfReply,workerName;

    public void clear() {
        answers.clear();
    }

    public void add(Answer ans){
        answers.add(ans);
        notifyDataSetChanged();
    }

    public ReplyQuestionsAdapter(Context context, String jobTitle, String phoneNumOfReply,String workerName) {
        this.context = context;
        this.jobTitle = jobTitle;
        this.phoneNumOfReply = phoneNumOfReply;
        this.workerName = workerName;
    }

    @NonNull
    @Override
    public PreviousQuestionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item,null,false);
        PreviousQuestionItemViewHolder viewHolder = new PreviousQuestionItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousQuestionItemViewHolder holder, int position) {
        holder.textViewAnswers.setText(answers.get(position).getReplay());
        holder.textViewPhone.setText(answers.get(position).getPhone());
        holder.textViewQuestion.setText(answers.get(position).getClientQuestion());
      //  holder.textViewPhoneOfClient.setText(answers.get(position).getPhoneOfClient());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneWorker = answers.get(position).getPhone();
                String reply = answers.get(position).getReplay();
                //String phone = answers.get(position).getPhoneOfClient();
                Toast.makeText(v.getContext(),phoneWorker, Toast.LENGTH_SHORT).show();
                Toast.makeText(v.getContext(), jobTitle, Toast.LENGTH_SHORT).show();
                Toast.makeText(v.getContext(), phoneNumOfReply, Toast.LENGTH_SHORT).show();
              /*  v.getContext().startActivity(new Intent(v.getContext(), CommentsForWorkerActivity.class)
                        .putExtra("phoneWorker",phoneWorker)
                        .putExtra("phoneClient",phoneNumOfReply)
                        .putExtra("jobTitle",jobTitle)
                        .putExtra("reply",reply)
                        .putExtra(EXTR_USER_NAME,workerName));*/
            }
        });


    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class PreviousQuestionItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAnswers,textViewPhone,textViewQuestion;
     //   TextView textViewPhoneOfClient;
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


