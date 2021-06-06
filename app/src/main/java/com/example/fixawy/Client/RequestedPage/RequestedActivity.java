package com.example.fixawy.Client.RequestedPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.fixawy.Client.EditPage.EditActivity;
import com.example.fixawy.Client.EditPage.EditActivityViewModel;
import com.example.fixawy.Client.HomePageClient.OnItemClick;
import com.example.fixawy.Client.MakeOrder.Repo.ClientOrderRepo;
import com.example.fixawy.Client.MakeOrder.pojos.OrderTree;
import com.example.fixawy.R;

import java.util.ArrayList;
import java.util.List;

public class RequestedActivity extends AppCompatActivity implements onitemclick {
    RecyclerView requestedRecyclerView;
    String phoneNum, categoryType;
    RequestedAdapter requestedAdapter;
    RequestedPageViewModel requestedPageViewModel;
    ArrayList<OrderTree> orderTreeItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested);
        phoneNum = getIntent().getStringExtra("phone");
        categoryType = getIntent().getExtras().getString("CategoryType");
        requestedRecyclerView = findViewById(R.id.requestedrv);
        requestedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestedPageViewModel = new ViewModelProvider(this).get(RequestedPageViewModel.class);
        requestedPageViewModel.requestedPageLiveData.observe(this, new Observer<List<OrderTree>>() {
            @Override
            public void onChanged(List<OrderTree> orderTrees) {
                requestedAdapter = new RequestedAdapter(RequestedActivity.this, orderTrees,RequestedActivity.this);
                requestedRecyclerView.setAdapter(requestedAdapter);
                requestedAdapter.notifyDataSetChanged();
            }
        });

        requestedPageViewModel.retrieveData(phoneNum);


    }


    /*@Override
    public void onItemClick(int position) {

            Intent intent = new Intent(RequestedActivity.this, EditActivity.class);
            intent.putExtra("phone", phoneNum);
            intent.putExtra("CategoryType", categoryType);
            intent.putExtra("uid", requestedPageViewModel.uIds.get(position));
            startActivity(intent);


    }*/


    @Override
    public void onclick(int position, int type) {
        if(type ==0) {
            Intent intent = new Intent(RequestedActivity.this, EditActivity.class);
            intent.putExtra("phone", phoneNum);
            intent.putExtra("CategoryType", categoryType);
            intent.putExtra("uid", requestedPageViewModel.uIds.get(position));
            startActivity(intent);
        }
        else if (type ==1){
            //orderTreeItems = new ArrayList<>();
           requestedAdapter.orderTreeItems.get(position);
           requestedAdapter.orderTreeItems.remove(position);
            requestedAdapter.notifyDataSetChanged();
            ClientOrderRepo repo = new ClientOrderRepo();
            repo.retrieveDataEdit(phoneNum,categoryType,requestedPageViewModel.uIds.get(position)).removeValue();

        }
    }
}

