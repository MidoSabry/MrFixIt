package com.example.fixawy.Client.HomePageClient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Pojos.AllCategory;
import com.example.fixawy.R;

import java.util.List;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {

    private Context context;

    public void setCategoryItemList(List<AllCategory> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    private List<AllCategory> categoryItemList;

    public CategoryItemRecyclerAdapter(Context context, List<AllCategory> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {

        holder.categoryNametv.setText(categoryItemList.get(position).getCategoryTitle());
    }

    @Override
    public int getItemCount() {
        if(categoryItemList == null){
            return 0;
        } else {
            return categoryItemList.size();
        }
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder{

        TextView categoryNametv;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryNametv = itemView.findViewById(R.id.categoryNameTextView);

        }
    }

}
