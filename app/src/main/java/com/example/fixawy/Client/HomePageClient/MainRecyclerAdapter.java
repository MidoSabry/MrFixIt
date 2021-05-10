package com.example.fixawy.Client.HomePageClient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.MainActivity;
import com.example.fixawy.Pojos.AllCategory;
import com.example.fixawy.R;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

      private Context context;
      AllCategoryNamesModel allCategoryNamesModel = new AllCategoryNamesModel();


    public MainRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        setCategoryRecyclerView(holder.categoryRecycler,allCategoryNamesModel.getAllCategories());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{


        RecyclerView categoryRecycler;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);


            categoryRecycler = itemView.findViewById(R.id.categoryrv);

        }
    }

    private void setCategoryRecyclerView(RecyclerView categoryRecyclerView,List<AllCategory>allCategories){


        CategoryItemRecyclerAdapter categoryItemRecyclerAdapter = new CategoryItemRecyclerAdapter(context,allCategories);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(categoryItemRecyclerAdapter);


    }


}
