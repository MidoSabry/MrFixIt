package com.example.fixawy.Worker.HomePageWorker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.fixawy.Firebase.FirebaseHandlerClient;
import com.example.fixawy.Pojos.MakeOrder;
import com.example.fixawy.R;
import com.example.fixawy.Worker.WorkerQuestions.WorkerQuestionsActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestedHomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    //DrawLayout side-menubar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String jobTitle,phoneWorker;

    //recyclerView
    RecyclerView recyclerView_worker_requested;
    FirebaseHandlerClient firebaseHandlerClient;
    RequestedItemRecyclerAdapter myAdapter;
    List<MakeOrder> list;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_home_page);

        jobTitle = getIntent().getStringExtra("jobTitle");
        phoneWorker=getIntent().getStringExtra("phone");

        //DrawLayout sidemenu-bar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view2);
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
        navigationView.setCheckedItem(R.id.nav_home2);




        recyclerView_worker_requested = findViewById(R.id.recyclerviewRequestedOwner);

        recyclerView_worker_requested.setHasFixedSize(true);
        recyclerView_worker_requested.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new RequestedItemRecyclerAdapter(this,list);
        recyclerView_worker_requested.setAdapter(myAdapter);
        //firebaseHandlerClient = new FirebaseHandlerClient();
        //firebaseHandlerClient.getAllMakeOrders(myAdapter);
        database = FirebaseDatabase.getInstance().getReference("Client").child("Order details");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MakeOrder makeOrder = dataSnapshot.getValue(MakeOrder.class);
                    list.add(makeOrder);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    //to select page from side menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_all_previous_questions:
                Intent intentPrevQuestions = new Intent(RequestedHomePageActivity.this, WorkerQuestionsActivity.class);
                intentPrevQuestions.putExtra("jobTitle",jobTitle);
                intentPrevQuestions.putExtra("phone",phoneWorker);
                startActivity(intentPrevQuestions);
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }






}