package com.example.fixawy.Client.ReplyQuestions;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

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


    public void clear() {
        answers.clear();
    }

    public void add(Answer ans){
        answers.add(ans);
        notifyDataSetChanged();
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

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class PreviousQuestionItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAnswers,textViewPhone;
        public View layout;
        public PreviousQuestionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            textViewAnswers = itemView.findViewById(R.id.answer);
            textViewPhone = itemView.findViewById(R.id.phone);

        }
    }
}

