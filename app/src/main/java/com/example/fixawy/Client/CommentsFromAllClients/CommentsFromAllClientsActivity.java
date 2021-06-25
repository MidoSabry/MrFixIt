package com.example.fixawy.Client.CommentsFromAllClients;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fixawy.Client.CommentOfReply.CommentOfReplyActivity;
import com.example.fixawy.Client.CommentOfReply.CommentOfReplyAdapter;
import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionActivity;
import com.example.fixawy.Client.ReplyForMyQuestion.ReplyForMyQuestionActivity;
import com.example.fixawy.Client.ReplyQuestions.AnswerActivity;
import com.example.fixawy.Pojos.Comment;
import com.example.fixawy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.example.fixawy.Share.VerifyCode.VerificationCode.EXTR_USER_NAME;
public class CommentsFromAllClientsActivity extends AppCompatActivity {

    DatabaseReference mRef;
    String phoneClient,jobTitle,phoneWorker,reply,clientName,phoneOfCard;
    Comment comment;
    CommentsFromAllClientsAdapter commentsFromAllClientsAdapter;
    RecyclerView mRecyclerView;
    FloatingActionButton floatingActionButtonOpenDialog;
    AlertDialog alertDialog;
    LayoutInflater inflater;
    Button btnAddReplyDialog,btnCancelDialog;
    EditText addComment;
    DatabaseReference databaseReference;
    FirebaseDatabase db ;
    ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_from_all_clients);
        imageViewBack = findViewById(R.id.backToPrevious);

        phoneClient = getIntent().getStringExtra("phoneClient");
        phoneWorker =  getIntent().getStringExtra("phoneWorker");
        jobTitle =  getIntent().getStringExtra("jobTitle");
        reply = getIntent().getStringExtra("reply");
        clientName = getIntent().getStringExtra(EXTR_USER_NAME);
        phoneOfCard = getIntent().getStringExtra("phoneOfCard");

        Toast.makeText(this, "phone of who reply"+phoneClient, Toast.LENGTH_SHORT).show();

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CommentsFromAllClientsActivity.this,"Back Phone is " + phoneWorker, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CommentsFromAllClientsActivity.this,AnswerActivity.class)
                        .putExtra("phoneClient",phoneClient)
                        //  .putExtra("phoneNum",phoneWorker)
                        .putExtra("jobTitle",jobTitle)
                        .putExtra("reply",reply)
                        .putExtra(EXTR_USER_NAME,clientName)
                        .putExtra("phoneOfCard",phoneOfCard));
            }
        });
        floatingActionButtonOpenDialog = findViewById(R.id.openDialogComment);
        floatingActionButtonOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(v.getContext()).create();
                inflater = LayoutInflater.from(v.getContext());
                View dialogView = inflater.inflate(R.layout.add_comment_dialog, null);
                btnAddReplyDialog= dialogView.findViewById(R.id.btn_addNoteDialog);
                btnCancelDialog=dialogView.findViewById(R.id.btn_cancelNoteDialog);
                addComment=dialogView.findViewById(R.id.add_comment);
                db = FirebaseDatabase.getInstance();
                databaseReference = db.getReference("Client");
                btnAddReplyDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String comment = addComment.getText().toString().trim();
                        Comment  commentModel = new Comment(phoneWorker,phoneClient,comment,reply);
                        databaseReference.child("Questions").child("Comments").child(jobTitle).child(phoneWorker).child(phoneClient).push().setValue(commentModel);
                        Toast.makeText(CommentsFromAllClientsActivity.this, "your comment will be added soon...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CommentsFromAllClientsActivity.this, PreviousQuestionActivity.class)
                                .putExtra("CategoryType",jobTitle)
                                .putExtra("phoneClient",phoneClient)
                                .putExtra(EXTR_USER_NAME,clientName));
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
        commentsFromAllClientsAdapter = new CommentsFromAllClientsAdapter(this,jobTitle);
        mRecyclerView = findViewById(R.id.questionsList);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(commentsFromAllClientsAdapter);
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
                        commentsFromAllClientsAdapter.add(comment);
                    }
                }
            }
        });

    }
    //backButton
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

        return;
    }
}
