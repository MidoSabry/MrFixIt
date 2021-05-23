package com.example.fixawy.Worker.WorkerQuestions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorkerQuestionsAdapter extends RecyclerView.Adapter<WorkerQuestionsAdapter.PreviousQuestionItemViewHolder> {

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
    public WorkerQuestionsAdapter.PreviousQuestionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.previous_question_item,null,false);
        WorkerQuestionsAdapter.PreviousQuestionItemViewHolder viewHolder = new WorkerQuestionsAdapter.PreviousQuestionItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerQuestionsAdapter.PreviousQuestionItemViewHolder holder, int position) {
        holder.textViewPhone.setText(questions.get(position).getPhone());
        holder.textViewQuestion.setText(questions.get(position).getQuestion());
        Picasso.get().load(questions.get(position).getImageUri()).into(holder.imageViewForQuestion);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class PreviousQuestionItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuestion,textViewPhone;
        ImageView imageViewForQuestion;
        public PreviousQuestionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPhone = itemView.findViewById(R.id.phone);
            textViewQuestion = itemView.findViewById(R.id.question);
            imageViewForQuestion=itemView.findViewById(R.id.imageFromQuestion);

        }
    }
}
