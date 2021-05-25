package com.example.fixawy.Client.HomePageClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fixawy.Client.HistoryPage.HistoryActivity;
import com.example.fixawy.Client.MakeOrder.ClientMakeOrder;
import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionActivity;
import com.example.fixawy.Client.RequestedPage.RequestedActivity;
import com.example.fixawy.Client.SelectKindOfChoicePage.SelectKindOfChoiceActivity;
import com.example.fixawy.MainActivity;
import com.example.fixawy.Pojos.AllCategory;
import com.example.fixawy.Pojos.EmployeeData;
import com.example.fixawy.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class HomePageClientActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnItemClick {
    RecyclerView mainRecyclerView;
    RecyclerView categoryRecyclerView;
    private Context context;
    private AllCategoryNamesModel allCategoryNamesModel;


    MainRecyclerAdapter mainRecyclerAdapter;
    CategoryItemRecyclerAdapter categoryItemRecyclerAdapter;


    //DrawLayout side-menubar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String phoneNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_client);

        phoneNum = getIntent().getStringExtra("phone");
        //DrawLayout sidemenu-bar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        //Hide or show items
//        Menu menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_logout).setVisible(false);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.primaryColor));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        List<EmployeeData> electricityEmployeeDataListm = new ArrayList<>();
        electricityEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));
        electricityEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));
        electricityEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));
        electricityEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));
        electricityEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));


        List<EmployeeData> pulmberEmployeeDataListm = new ArrayList<>();
        pulmberEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));
        pulmberEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));
        pulmberEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));
        pulmberEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));
        pulmberEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));


        List<EmployeeData> carpenterEmployeeDataListm = new ArrayList<>();
        carpenterEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));
        carpenterEmployeeDataListm.add(new EmployeeData(1, "Mohamed sabry", "sidibeshr", null, "01225699594", 3));
//        carpenterEmployeeDataListm.add(new EmployeeData(1,"Mohamed sabry","sidibeshr",null,"01225699594",3));
//        carpenterEmployeeDataListm.add(new EmployeeData(1,"Mohamed sabry","sidibeshr",null,"01225699594",3));
//        carpenterEmployeeDataListm.add(new EmployeeData(1,"Mohamed sabry","sidibeshr",null,"01225699594",3));


        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Electricity", electricityEmployeeDataListm));
        allCategoryList.add(new AllCategory(2, "Pulmber", pulmberEmployeeDataListm));
        allCategoryList.add(new AllCategory(3, "Carpenter", carpenterEmployeeDataListm));


        //MainRecyclerView
        mainRecyclerView = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoryList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);


        allCategoryNamesModel = new AllCategoryNamesModel();


        //CategoryRecyclerView
        categoryRecyclerView = findViewById(R.id.categoryrv);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        categoryItemRecyclerAdapter = new CategoryItemRecyclerAdapter(this, allCategoryNamesModel.getAllCategories(), this);
        categoryRecyclerView.setAdapter(categoryItemRecyclerAdapter);
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }


    //to select page from side menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_requested:
                Intent intent = new Intent(HomePageClientActivity.this, RequestedActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_previous_requested:
                Intent intent2 = new Intent(HomePageClientActivity.this, HistoryActivity.class);
                startActivity(intent2);
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(int position) {
        AllCategory selectCategory = allCategoryNamesModel.getAllCategories().get(position);
        Intent intent = new Intent(HomePageClientActivity.this, ClientMakeOrder.class);
        intent.putExtra("CategoryType", selectCategory.getCategoryTitle());
        intent.putExtra("phone", phoneNum);
        startActivity(intent);

    }

}