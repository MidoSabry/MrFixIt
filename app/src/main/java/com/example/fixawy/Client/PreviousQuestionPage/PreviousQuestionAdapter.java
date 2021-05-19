package com.example.fixawy.Client.PreviousQuestionPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.RequestedPage.RequestedAdapter;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.R;

import java.util.ArrayList;

public class PreviousQuestionAdapter extends RecyclerView.Adapter<PreviousQuestionAdapter.PreviousQuestionItemViewHolder> {

    ArrayList<Questions> questions = new ArrayList<>();
    public void clear() {
        questions.clear();
    }

    public void add(Questions ques){
        questions.add(ques);
        notifyDataSetChanged();
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

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class PreviousQuestionItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuestion,textViewPhone;
        public PreviousQuestionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPhone = itemView.findViewById(R.id.phone);
            textViewQuestion = itemView.findViewById(R.id.question);
        }
    }
}
