package com.example.fixawy.Client.HomePageClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.fixawy.MainActivity;
import com.example.fixawy.Pojos.AllCategory;
import com.example.fixawy.R;

import java.util.List;

public class HomePageClientActivity extends AppCompatActivity {
    RecyclerView mainRecyclerView;
    RecyclerView categoryRecyclerView;
    private Context context;


    MainRecyclerAdapter mainRecyclerAdapter;
    CategoryItemRecyclerAdapter categoryItemRecyclerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_client);


        //MainRecyclerView
        mainRecyclerView = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);


        //CategoryRecyclerView
//        categoryRecyclerView = findViewById(R.id.categoryrv);
//        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
//        categoryItemRecyclerAdapter = new CategoryItemRecyclerAdapter();
//        categoryRecyclerView.setAdapter(categoryItemRecyclerAdapter);
//        //homePageViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
//        homePageViewModel = ViewModelProviders.of(this).get(HomePageViewModel.class);
//
//        homePageViewModel.getcategoryModelData().observe(this, new Observer<List<AllCategory>>() {
//            @Override
//            public void onChanged(List<AllCategory> movies) {
//                categoryItemRecyclerAdapter.setCategoryItemList(movies);
//                categoryItemRecyclerAdapter.notifyDataSetChanged();
//            }
//        });




    }
}