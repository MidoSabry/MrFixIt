package com.example.fixawy.Client.HomePageClient;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fixawy.Client.HistoryPage.HistoryActivity;
import com.example.fixawy.Client.RequestedPage.RequestedActivity;
import com.example.fixawy.Client.SelectKindOfChoicePage.SelectKindOfChoiceActivity;
import com.example.fixawy.MainActivity;
import com.example.fixawy.Pojos.AllCategory;
//import com.example.fixawy.Pojos.EmployeeData;
import com.example.fixawy.Pojos.JobTitleCategory;
import com.example.fixawy.Pojos.User;
import com.example.fixawy.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

import static android.content.Intent.EXTRA_PHONE_NUMBER;
import static com.example.fixawy.Share.VerifyCode.VerificationCode.EXTR_PHONE_NUM;
import static com.example.fixawy.Share.VerifyCode.VerificationCode.EXTR_USER_NAME;


public class HomePageClientActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CategoryItemRecyclerAdapter.onItemClickListener {
    RecyclerView mainRecyclerView;
    RecyclerView categoryRecyclerView;
    private Context context;
    private AllCategoryNamesModel allCategoryNamesModel;
    DatabaseReference database;
    TextView textViewUserName,textViewUserPhone;
    String client_phone_num,client_user_name;

    public static final String EXTRA_CATEGORY_NAME = "categoryName";


    MainRecyclerAdapter mainRecyclerAdapter;
    CategoryItemRecyclerAdapter categoryItemRecyclerAdapter;





    //DrawLayout side-menubar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_client);


        //get data from verifiaction code page
        Intent intent = getIntent();
        client_phone_num = intent.getStringExtra(EXTR_PHONE_NUM);
        client_user_name = intent.getStringExtra(EXTR_USER_NAME);
        Toast.makeText(this, client_user_name, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, client_phone_num, Toast.LENGTH_SHORT).show();






        //DrawLayout sidemenu-bar
        //start
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        //Hide or show items
//        Menu menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_logout).setVisible(false);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.primaryColor));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        //show username and phone on side menuebar
        View headerView= navigationView.getHeaderView(0);
        textViewUserName = headerView.findViewById(R.id.header_client_name);
        textViewUserPhone = headerView.findViewById(R.id.header_client_phone);
        textViewUserName.setText(client_user_name);
        textViewUserPhone.setText(client_phone_num);
        //end


        //Categories buttons
        allCategoryNamesModel = new AllCategoryNamesModel();
        //CategoryRecyclerView
        categoryRecyclerView = findViewById(R.id.categoryrv);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        categoryItemRecyclerAdapter = new CategoryItemRecyclerAdapter(this,allCategoryNamesModel.getAllCategories());
        categoryRecyclerView.setAdapter(categoryItemRecyclerAdapter);
        categoryItemRecyclerAdapter.setOnItemClickListener(HomePageClientActivity.this);




        //Lists of employees
        List<User> employeeDataListElectricity = new ArrayList<>();
        List<User> employeeDataListCarpenter = new ArrayList<>();
        List<User> employeeDataListPulmber = new ArrayList<>();
        List<User> employeeDataListPainter = new ArrayList<>();
        List<User> employeeDataListTilesHandy = new ArrayList<>();
        List<User> employeeDataListMason = new ArrayList<>();
        List<User> employeeDataListSmith = new ArrayList<>();
        List<User> employeeDataListParquet = new ArrayList<>();
        List<User> employeeDataListGypsum = new ArrayList<>();
        List<User> employeeDataListMarble = new ArrayList<>();
        List<User> employeeDataListAlumetal = new ArrayList<>();
        List<User> employeeDataListGlasses = new ArrayList<>();
        List<User> employeeDataListWoodPainter = new ArrayList<>();
        List<User> employeeDataListCurtains = new ArrayList<>();
        List<User> employeeDataListSatellite = new ArrayList<>();
        List<User> employeeDataListAppliances = new ArrayList<>();
        List<AllCategory>allCategoryList = new ArrayList<>();
        AllCategory allCategory = new AllCategory();
//        HashMap<String,List<EmployeeData>> myMap= new HashMap<String,List<EmployeeData>>();
//        myMap.put("Electricity",employeeDataListElectricity);
//                myMap.put("Carpenter",employeeDataListCarpenter);
//
//
//                myMap.get("Electricity");


        //        allCategoryList.add(new AllCategory(1,"Electricity",electricityEmployeeDataListm));
//        allCategoryList.add(new AllCategory(2,"Pulmber",pulmberEmployeeDataListm));
//        allCategoryList.add(new AllCategory(3,"Carpenter",carpenterEmployeeDataListm));









        //MainRecyclerView
        mainRecyclerView = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this,allCategoryList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);


        //retrive employee of carpenter
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Carpenter").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("ssssss","user"+employeeData.getUserName());
                    employeeDataListCarpenter.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Electricity
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Electricity").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListElectricity.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Plumber
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Plumber").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListPulmber.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Painter
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Painter").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListPainter.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of TilesHandyMan
        database = FirebaseDatabase.getInstance().getReference("Worker").child("TilesHandyMan").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListTilesHandy.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Mason
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Mason").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListMason.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Smith
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Smith").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListSmith.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Parquet
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Parquet").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListParquet.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Gypsum
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Gypsum").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListGypsum.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Marble
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Marble").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListMarble.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Alumetal
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Alumetal").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListAlumetal.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Glasses
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Glasses").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("glass","user"+employeeData.getUserName());
                    employeeDataListGlasses.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of WoodPainter
        database = FirebaseDatabase.getInstance().getReference("Worker").child("WoodPainter").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData2 = dataSnapshot.getValue(User.class);
                    Log.d("wooooo","user"+employeeData2.getUserName());
                    employeeDataListWoodPainter.add(employeeData2);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //retrive employee of Curtains
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Curtains").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("looooooo","user"+employeeData.getUserName());
                    employeeDataListCurtains.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //retrive employee of Satellite
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Satellite").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("5555","user"+employeeData.getUserName());
                    employeeDataListSatellite.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //retrive employee of Appliances
        database = FirebaseDatabase.getInstance().getReference("Worker").child("Appliances").child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = dataSnapshot.getValue(User.class);
                    Log.d("mmmm","user"+employeeData.getUserName());
                    employeeDataListAppliances.add(employeeData);
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        allCategoryList.add(new AllCategory(1,"Electricity",employeeDataListElectricity));
        allCategoryList.add(new AllCategory(2,"Carpenter",employeeDataListCarpenter));
        allCategoryList.add(new AllCategory(3,"Plumber",employeeDataListPulmber));
        allCategoryList.add(new AllCategory(4,"Painter",employeeDataListPainter));
        allCategoryList.add(new AllCategory(5,"TilesHandyMan",employeeDataListTilesHandy));
        allCategoryList.add(new AllCategory(6,"Mason",employeeDataListMason));
        allCategoryList.add(new AllCategory(7,"Smith",employeeDataListSmith));
        allCategoryList.add(new AllCategory(8,"Parquet",employeeDataListParquet));
        allCategoryList.add(new AllCategory(9,"Gypsum",employeeDataListGypsum));
        allCategoryList.add(new AllCategory(10,"Marble",employeeDataListMarble));
        allCategoryList.add(new AllCategory(11,"Alumetal",employeeDataListAlumetal));
        allCategoryList.add(new AllCategory(12,"Glasses",employeeDataListGlasses));
        allCategoryList.add(new AllCategory(13,"WoodPainter",employeeDataListWoodPainter));
        allCategoryList.add(new AllCategory(14,"Curtains",employeeDataListCurtains));
        allCategoryList.add(new AllCategory(15,"Satellite",employeeDataListSatellite));
        allCategoryList.add(new AllCategory(16,"Appliances Maintenance",employeeDataListAppliances));


//        myMap.forEach(new BiConsumer<String, List<EmployeeData>>(){
//            @Override
//            public void accept(String key, List<EmployeeData> employeeData) {
//                Log.d("ssssss",key +" "+employeeData);
//            }
//        });

        //retrive all categories in firebase
//        database = FirebaseDatabase.getInstance().getReference("Worker").getRoot();
//
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    JobTitleCategory jobTitleCategory = dataSnapshot.getValue(JobTitleCategory.class);
//                    Log.d("ssssss","user"+allCategory.getCategoryTitle());
//                    allCategoryList.add(allCategory);
//                }
//                mainRecyclerAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });










    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }


    //to select page from side menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
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


    //To click on buttons of categories
    AllCategoryNamesModel allCategoryNamesModel2 = new AllCategoryNamesModel();
    @Override
    public void onItemClick(int position) {
        Intent selectTypeIntent = new Intent(this, SelectKindOfChoiceActivity.class);
        AllCategory clickedItem = allCategoryNamesModel2.getAllCategories().get(position);
        Log.d("Mido", clickedItem.getCategoryTitle());
        selectTypeIntent.putExtra(EXTRA_CATEGORY_NAME,clickedItem.getCategoryTitle());
        selectTypeIntent.putExtra("phone",client_phone_num);
        // Toast.makeText(context, clickedItem.getCategoryTitle(), Toast.LENGTH_SHORT).show();
        startActivity(selectTypeIntent);
    }
}