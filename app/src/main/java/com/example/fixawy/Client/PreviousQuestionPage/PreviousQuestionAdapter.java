package com.example.fixawy.Client.PreviousQuestionPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Client.RequestedPage.RequestedAdapter;
import com.example.fixawy.R;

public class PreviousQuestionAdapter extends RecyclerView.Adapter<PreviousQuestionAdapter.PreviousQuestionItemViewHolder> {
    @NonNull
    @Override
    public PreviousQuestionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.previous_question_item,null,false);
        PreviousQuestionAdapter.PreviousQuestionItemViewHolder viewHolder = new PreviousQuestionAdapter.PreviousQuestionItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousQuestionItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PreviousQuestionItemViewHolder extends RecyclerView.ViewHolder {
        public PreviousQuestionItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
