package com.example.fixawy.Client.MakeOrder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.fixawy.Client.MakeOrder.Repo.ClientOrderRepo;
import com.example.fixawy.Client.MakeOrder.pojos.OrderTree;

public class ThirdOrderViewModel extends AndroidViewModel {
    public ThirdOrderViewModel(@NonNull Application application) {
        super(application);
    }

    public void addData(OrderTree orderTree) {
        ClientOrderRepo clientOrderRepo = new ClientOrderRepo();
        clientOrderRepo.addData().push().setValue(orderTree);
    }

}

