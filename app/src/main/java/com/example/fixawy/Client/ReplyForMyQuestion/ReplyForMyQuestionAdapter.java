package com.example.fixawy.Client.ReplyForMyQuestion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Pojos.Answer;
import com.example.fixawy.R;

import java.util.ArrayList;

public class ReplyForMyQuestionAdapter extends RecyclerView.Adapter<ReplyForMyQuestionAdapter.PreviousQuestionItemViewHolder> {

    ArrayList<Answer> answers = new ArrayList<>();


    public void clear() {
        answers.clear();
    }

    public void add(Answer ans){
        answers.add(ans);
        notifyDataSetChanged();
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
        holder.textViewPhoneOfClient.setText(answers.get(position).getPhoneOfClient());

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class PreviousQuestionItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAnswers,textViewPhone,textViewQuestion,textViewPhoneOfClient;
        public View layout;
        public PreviousQuestionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            textViewAnswers = itemView.findViewById(R.id.answer);
            textViewPhone = itemView.findViewById(R.id.phone);
            textViewQuestion = itemView.findViewById(R.id.question);
            textViewPhoneOfClient = itemView.findViewById(R.id.phoneOfClient);
        }
    }
}

