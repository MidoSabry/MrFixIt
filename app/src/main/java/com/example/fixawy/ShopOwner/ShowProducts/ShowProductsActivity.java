package com.example.fixawy.ShopOwner.ShowProducts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fixawy.Client.AskQuestionPage.AskQuestionActivity;
import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionActivity;
import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionAdapter;
import com.example.fixawy.Pojos.Product;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.R;
import com.example.fixawy.ShopOwner.AddNewProduct.AddNewProductActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowProductsActivity extends AppCompatActivity {

    FloatingActionButton floatingButtonAsk;
    String phoneNum,shopType,shopName;
    RecyclerView mRecyclerView;
    DatabaseReference mRef;
    ShowProductAdapter showProductAdapter;
    Product product;
    TextView textViewJobTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);

        mRecyclerView = findViewById(R.id.productList);
        floatingButtonAsk = findViewById(R.id.addProduct);
        textViewJobTitle=findViewById(R.id.txt_job_title);
        phoneNum = getIntent().getStringExtra("phone");
        shopType = getIntent().getStringExtra("shopType");
        shopName = getIntent().getStringExtra("shopName");

        textViewJobTitle.setText("All Products for "+shopName);

        floatingButtonAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowProductsActivity.this, AddNewProductActivity.class);
                intent.putExtra("phone", phoneNum);
                intent.putExtra("shopType",shopType);
                intent.putExtra("shopName",shopName);
                startActivity(intent);
            }
        });

        mRef = FirebaseDatabase.getInstance().getReference();
        showProductAdapter = new ShowProductAdapter(ShowProductsActivity.this,phoneNum,shopName,shopType);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(showProductAdapter);
        readData();
    }
    // return all questions for specific job...
    public void readData(){
        mRef.child("ShopOwner").child(shopType).child("Product Posts").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    product = snapshot.getValue(Product.class);
                    showProductAdapter.add(product);
                }
            }
        });
    }
}