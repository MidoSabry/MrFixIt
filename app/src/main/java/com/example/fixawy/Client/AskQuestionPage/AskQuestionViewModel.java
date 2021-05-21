package com.example.fixawy.Client.AskQuestionPage;

import androidx.lifecycle.ViewModel;
import com.example.fixawy.Firebase.FirebaseHandlerClient;
import com.example.fixawy.Firebase.FirebaseHandlerWorker;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.Pojos.User;


public class AskQuestionViewModel extends ViewModel {

    private FirebaseHandlerClient firebaseHandlerClient;
    private FirebaseHandlerWorker firebaseHandlerWorker;


    public void addClientQuestion(Questions question){
        firebaseHandlerClient = new FirebaseHandlerClient();
        firebaseHandlerClient.addClientQuestion(question,question.phone,question.jobTitle).addOnSuccessListener(suc->{

        });
    }

    public void addWorkerQuestion(Questions question){
        firebaseHandlerWorker = new FirebaseHandlerWorker();
        firebaseHandlerWorker.addWorkerQuestion(question,question.phone,question.jobTitle).addOnSuccessListener(suc->{

        });
    }
}
