package com.example.fixawy.Client.PreviousQuestionPage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fixawy.Client.ReplyQuestions.AnswerActivity;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PreviousQuestionAdapter extends RecyclerView.Adapter<PreviousQuestionAdapter.PreviousQuestionItemViewHolder> {

    ArrayList<Questions> questions = new ArrayList<>();
    String phoneNum;
    Context context;

    public void clear() {
        questions.clear();
    }


    public void add(Questions ques){
        questions.add(ques);
        notifyDataSetChanged();
    }

    public PreviousQuestionAdapter(Context context, String phoneNum) {
        this.context = context;
        this.phoneNum = phoneNum;
    }

    @NonNull
    @Override
    public PreviousQuestionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.previous_question_item,null,false);
        PreviousQuestionAdapter.PreviousQuestionItemViewHolder viewHolder = new PreviousQuestionAdapter.PreviousQuestionItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousQuestionItemViewHolder holder, int position) {
        holder.textViewPhone.setText(questions.get(position).getPhone());
        holder.textViewQuestion.setText(questions.get(position).getQuestion());
        Picasso.get().load(questions.get(position).getImageUri()).into(holder.imageViewForQuestion);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobTitle = questions.get(position).getJobTitle();
                String phone = holder.textViewPhone.getText().toString();
                v.getContext().startActivity(new Intent(v.getContext(), AnswerActivity.class)
                        .putExtra("phone",phone).putExtra("jobTitle",jobTitle)
                        .putExtra("phoneNum",phoneNum));
            }
        });

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class PreviousQuestionItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuestion,textViewPhone;
        ImageView imageViewForQuestion;
        public View layout;
        public PreviousQuestionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            textViewPhone = itemView.findViewById(R.id.phone);
            textViewQuestion = itemView.findViewById(R.id.question);
            imageViewForQuestion=itemView.findViewById(R.id.imageFromQuestion);

        }
    }
}
