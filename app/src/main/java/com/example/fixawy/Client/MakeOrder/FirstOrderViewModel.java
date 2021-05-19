package com.example.fixawy.Client.MakeOrder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fixawy.Client.MakeOrder.Repo.ClientOrderRepo;
import com.example.fixawy.Client.MakeOrder.pojos.OrderTree;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class FirstOrderViewModel extends AndroidViewModel {
//public MutableLiveData<OrderTree> apilivedata ;
    public FirstOrderViewModel(@NonNull Application application) {
        super(application);
    }

   public void addData(OrderTree orderTree) {
        ClientOrderRepo clientOrderRepo = new ClientOrderRepo();
        clientOrderRepo.addData().setValue(orderTree);
        //apilivedata = new MutableLiveData<>();
        //apilivedata.postValue(orderTree);
    }

}
