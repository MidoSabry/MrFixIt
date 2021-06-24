package com.example.fixawy.Client.CommentOfReply;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fixawy.Client.AllPreviousQuestions.AllPreviousQuestionsActivity;
import com.example.fixawy.Client.ReplyForMyQuestion.ReplyForMyQuestionActivity;
import com.example.fixawy.Client.ReplyForMyQuestion.ReplyForMyQuestionAdapter;
import com.example.fixawy.Pojos.Answer;
import com.example.fixawy.Pojos.Comment;
import com.example.fixawy.R;
import com.example.fixawy.Worker.ReplayQuestionsPage.ReplayQuestionActivity;
import com.example.fixawy.Worker.WorkerQuestions.WorkerQuestionsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CommentOfReplyActivity extends AppCompatActivity {

    DatabaseReference mRef;
    String phoneClient,jobTitle,phoneWorker,reply;
    Comment comment;
    CommentOfReplyAdapter commentOfReplyAdapter;
    RecyclerView mRecyclerView;
    FloatingActionButton floatingActionButtonOpenDialog;
    AlertDialog alertDialog;
    LayoutInflater inflater;
    Button btnAddReplyDialog,btnCancelDialog;
    EditText addReplyText;
    DatabaseReference databaseReference;
    FirebaseDatabase db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_of_reply);

        phoneClient = getIntent().getStringExtra("phoneClient");
        phoneWorker =  getIntent().getStringExtra("phoneWorker");
        jobTitle =  getIntent().getStringExtra("jobTitle");
        reply = getIntent().getStringExtra("reply");

        floatingActionButtonOpenDialog = findViewById(R.id.openDialogComment);
        floatingActionButtonOpenDialog.setOnClickListener(new View.OnClickListener() {
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
                        String comment = addReplyText.getText().toString().trim();
                        Comment  commentModel = new Comment(phoneWorker,phoneClient,comment,reply);
                        databaseReference.child("Questions").child("Comments").child(jobTitle).child(phoneWorker).child(phoneClient).push().setValue(commentModel);
                        Toast.makeText(CommentOfReplyActivity.this, "your comment will be added soon...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CommentOfReplyActivity.this, AllPreviousQuestionsActivity.class)
                                .putExtra("phone",phoneClient));
                        alertDialog.cancel();
                    }
                });
                btnCancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {alertDialog.cancel();}
                });
                alertDialog.setView(dialogView);
                alertDialog.show();
            }
        });



        mRef = FirebaseDatabase.getInstance().getReference();
        commentOfReplyAdapter = new CommentOfReplyAdapter(this,jobTitle);
        mRecyclerView = findViewById(R.id.questionsList);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(commentOfReplyAdapter);
        read();
        Toast.makeText(this, "all comments of reply", Toast.LENGTH_SHORT).show();
    }

    public void read(){
        mRef.child("Client").child("Questions").child("Comments").child(jobTitle).child(phoneWorker).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        comment = dataSnapshot.getValue(Comment.class);
                        commentOfReplyAdapter.add(comment);
                    }
                }
            }
        });

    }

}
