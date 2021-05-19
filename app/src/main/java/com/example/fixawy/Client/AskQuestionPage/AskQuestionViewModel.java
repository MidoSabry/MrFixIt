package com.example.fixawy.Client.AskQuestionPage;

import androidx.lifecycle.ViewModel;
import com.example.fixawy.Firebase.FirebaseHandlerClient;
import com.example.fixawy.Pojos.Questions;


public class AskQuestionViewModel extends ViewModel {

    private FirebaseHandlerClient firebaseHandlerClient;


    public void addClientQuestion(Questions question){
        firebaseHandlerClient = new FirebaseHandlerClient();
        firebaseHandlerClient.addClientQuestion(question,question.phone).addOnSuccessListener(suc->{

        });
    }
}
