package com.example.fixawy.Worker.WorkerQuestions;

import android.content.Context;
import android.text.Editable;
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
import com.example.fixawy.Pojos.User;
import com.example.fixawy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class WorkerQuestionsAdapter extends RecyclerView.Adapter<WorkerQuestionsAdapter.PreviousQuestionItemViewHolder> {

    ArrayList<Questions> questions = new ArrayList<>();
    Answer answerModel;
    AlertDialog alertDialog;
    LayoutInflater inflater;
    Button btnAddReplyDialog,btnCancelDialog;
    EditText addReplyText;
    DatabaseReference databaseReference;
    FirebaseDatabase db ;
    String reply,jobTitle,phone;



    Context context;
    String phoneWorker;

    public WorkerQuestionsAdapter(WorkerQuestionsActivity workerQuestionsActivity, String phoneWorker) {
        this.context = workerQuestionsActivity;
        this.phoneWorker = phoneWorker;

    }


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

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(v.getContext()).create();
                inflater = LayoutInflater.from(v.getContext());
                View dialogView = inflater.inflate(R.layout.add_reply_dialog, null);
                btnAddReplyDialog= dialogView.findViewById(R.id.btn_addNoteDialog);
                btnCancelDialog=dialogView.findViewById(R.id.btn_cancelNoteDialog);
                addReplyText=dialogView.findViewById(R.id.txt_addNoteDialog);
                db = FirebaseDatabase.getInstance();
                databaseReference = db.getReference("Client");
                btnAddReplyDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(v.getContext(),holder.textViewPhone.getText(), Toast.LENGTH_SHORT).show();
                        reply = addReplyText.getText().toString().trim();
                        jobTitle = questions.get(position).getJobTitle();
                        phone = holder.textViewPhone.getText().toString();

                        answerModel = new Answer(reply,phoneWorker);

                        databaseReference.child("Questions").child(jobTitle).child(phone).child("Reply").child(phoneWorker).setValue(answerModel);
                        alertDialog.cancel();
                    }
                });
                btnCancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        Toast.makeText(view.getContext(),phoneWorker, Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                });
                alertDialog.setView(dialogView);
                alertDialog.show();

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
