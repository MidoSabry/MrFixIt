package com.example.fixawy.Client.RequestedPage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fixawy.Client.MakeOrder.Repo.ClientOrderRepo;
import com.example.fixawy.Client.MakeOrder.pojos.OrderTree;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.okhttp.internal.DiskLruCache;

import java.util.ArrayList;
import java.util.List;

public class RequestedPageViewModel extends AndroidViewModel {
    public MutableLiveData<List<OrderTree>> requestedPageLiveData = new MutableLiveData<>();
    OrderTree orderTree;
    List<OrderTree> orderTreeList;
    public RequestedPageViewModel(@NonNull Application application) {
        super(application);
    }
    void retrieveData(String phoneNum, String category){
        ClientOrderRepo clientOrderRepo = new ClientOrderRepo();
        orderTreeList = new ArrayList<>();
        clientOrderRepo.retrieveData(phoneNum,category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    orderTree = postSnapshot.getValue(OrderTree.class);
                    //orderTree = snapshot.getValue(OrderTree.class);
                    orderTreeList.add(orderTree);
                }
                requestedPageLiveData.postValue(orderTreeList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}


